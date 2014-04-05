package com.flipper83.protohipster.feed.view.viewmodel;

import com.flipper83.protohipster.feed.domain.boundaries.FeedBoundary;
import com.flipper83.protohipster.feed.domain.boundaries.HipsterBoundary;
import com.flipper83.protohipster.feed.domain.interactors.GetFeed;
import com.flipper83.protohipster.feed.domain.interactors.GetMyLikers;
import com.flipper83.protohipster.feed.domain.interactors.LikeHipster;
import com.flipper83.protohipster.feed.view.ui.callback.RefreshFeedCallback;
import com.flipper83.protohipster.feed.view.viewmodel.callback.GetFeedCallback;
import com.flipper83.protohipster.feed.view.viewmodel.callback.GetMyLikersUiCallback;
import com.flipper83.protohipster.feed.view.viewmodel.callback.LikeHipsterCallback;
import com.flipper83.protohipster.feed.view.viewmodel.mapper.HipsterViewMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A list of view information Hipsters
 */
public class FeedViewModel {

    HipsterViewMapper hipsterViewMapper;
    GetFeed getFeed;
    GetMyLikers getMyLikers;
    LikeHipster likeUser;

    private List<HipsterViewModel> hipsters = new ArrayList<HipsterViewModel>();
    private RefreshFeedCallback refreshFeedCallback;
    private RefreshFeedCallback likeRefreshCallback;

    public FeedViewModel(GetFeed getFeed, GetMyLikers getMyLikers, LikeHipster likeUser, HipsterViewMapper mapper) {
        this.getFeed = getFeed;
        this.getMyLikers = getMyLikers;
        this.likeUser = likeUser;
        this.hipsterViewMapper = mapper;
    }

    public void addHipster(HipsterViewModel hipster) {
        hipsters.add(hipster);
    }

    public List<HipsterViewModel> getHipsters() {
        return Collections.unmodifiableList(hipsters);
    }

    public void populateFeed(RefreshFeedCallback populateFeedCallback) {
        this.refreshFeedCallback = populateFeedCallback;

        getFeed.getFeed(getFeedCallback);

    }


    GetFeedCallback getFeedCallback = new GetFeedCallback() {
        @Override
        public void feedReady(FeedBoundary feedBoundary) {
            hipsters.clear();

            for (HipsterBoundary hipsterBoundary : feedBoundary.getHipsters()) {

                HipsterViewModel hipsterViewModel = hipsterViewMapper.mapper(hipsterBoundary);
                hipsters.add(hipsterViewModel);
            }

            getMyLikers.getMyLikers(getMyLikersUiCallback);

        }
    };

    GetMyLikersUiCallback getMyLikersUiCallback = new GetMyLikersUiCallback() {
        @Override
        public void myLikersReady(List<String> myLikers) {
            for (HipsterViewModel hipster : hipsters) {
                boolean likedByMe = myLikers.contains(hipster.getUserId());
                hipster.setLikedByMe(likedByMe);
            }
            refreshFeedCallback.refreshPopulated();
        }
    };

    public void likeHipster(int position, RefreshFeedCallback likeRefreshCallback) {
        this.likeRefreshCallback = likeRefreshCallback;
        HipsterViewModel hipster = hipsters.get(position);
        hipster.setLikedByMe(true);

        likeUser.like(hipster.getUserId(), likeHipsterCallback);

    }

    LikeHipsterCallback likeHipsterCallback = new LikeHipsterCallback() {
        @Override
        public void liked(String userId) {
            for (HipsterViewModel hipsterViewModel : hipsters) {
                if (hipsterViewModel.getUserId().equals(userId)) {
                    hipsterViewModel.setRating(hipsterViewModel.getRating() + 1);
                    hipsterViewModel.setLikedByMe(true);
                }
            }
            likeRefreshCallback.refreshPopulated();
        }
    };

}

