package com.flipper83.protohipster.feed.domain.module;

import com.flipper83.protohipster.feed.datasource.interfaces.LikeDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.UserDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetCountLikesCallback;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetUserCallback;
import com.flipper83.protohipster.feed.domain.boundaries.FeedBoundary;
import com.flipper83.protohipster.feed.domain.boundaries.HipsterBoundary;
import com.flipper83.protohipster.feed.domain.gateway.Hipster;
import com.flipper83.protohipster.feed.domain.interactors.GetFeed;
import com.flipper83.protohipster.feed.domain.mappers.HipsterMapper;
import com.flipper83.protohipster.feed.view.viewmodel.callback.GetFeedCallback;
import com.flipper83.protohipster.globalutils.rating.RatingCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * this is an implementation for feed
 */
class GetFeedImpl implements GetFeed {


    private final HipsterMapper hipsterMapper;
    UserDataSource userDataSource;
    LikeDataSource likeDataSource;
    RatingCalculator ratingCalculator;

    public List<Hipster> hipsters;
    private GetFeedCallback getFeedCallback;

    @Inject
    GetFeedImpl(UserDataSource userDataSource, LikeDataSource likeDataSource, RatingCalculator ratingCalculator, HipsterMapper hipsterMapper) {
        this.userDataSource = userDataSource;
        this.likeDataSource = likeDataSource;
        this.ratingCalculator = ratingCalculator;
        this.hipsterMapper = hipsterMapper;
    }

    @Override
    public void getFeed(GetFeedCallback getFeedCallback) {

        this.getFeedCallback = getFeedCallback;
        userDataSource.getUsers(getUserCallback);

    }


    GetUserCallback getUserCallback = new GetUserCallback() {

        @Override
        public void usersReady(List<Hipster> hipsters) {
            List<String> usersIdsForLikers = new ArrayList<String>();

            for (Hipster hipster : hipsters) {
                usersIdsForLikers.add(hipster.getUserId());
            }

            GetFeedImpl.this.hipsters = hipsters;
            likeDataSource.getCountLikesForUser(usersIdsForLikers, getCountLikesCallback);
        }
    };

    GetCountLikesCallback getCountLikesCallback = new GetCountLikesCallback() {
        @Override
        public void countUsers(Map<String, Integer> countLikers) {
            for (Hipster hipster : hipsters) {
                if (countLikers.containsKey(hipster.getUserId())) {

                    int totalLikes = countLikers.get(hipster.getUserId());
                    hipster.setNumLikes(totalLikes);
                } else {
                    hipster.setNumLikes(0);
                }
            }

            FeedBoundary feedBoundary = new FeedBoundary();
            for (Hipster hipster : hipsters) {
                HipsterBoundary hipsterBoundary = hipsterMapper.mapper(hipster);


                if (hipster.getNumLikes() != 0) {
                    int rating = ratingCalculator.calculate(hipster.getNumLikes());
                    hipsterBoundary.setRating(rating);
                } else {
                    hipsterBoundary.setRating(ratingCalculator.defaultValue());
                }

                feedBoundary.add(hipsterBoundary);
            }

            getFeedCallback.feedReady(feedBoundary);
        }
    };

}
