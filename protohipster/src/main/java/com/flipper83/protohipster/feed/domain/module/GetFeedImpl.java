package com.flipper83.protohipster.feed.domain.module;

import com.flipper83.protohipster.feed.datasource.interfaces.LikeDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.UserDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetCountLikesCallback;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetUserCallback;
import com.flipper83.protohipster.feed.domain.boundaries.FeedBoundary;
import com.flipper83.protohipster.feed.domain.boundaries.HipsterBoundary;
import com.flipper83.protohipster.feed.domain.mappers.HipsterMapper;
import com.flipper83.protohipster.feed.domain.gateway.Hipster;
import com.flipper83.protohipster.feed.domain.interactors.GetFeed;
import com.flipper83.protohipster.globalutils.rating.RatingCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import rx.util.functions.Func1;
import rx.util.functions.Func2;

/**
 * this is an implementation for feed
 */
class GetFeedImpl  implements GetFeed {


    private final HipsterMapper hipsterMapper;
    UserDataSource userDataSource;
    LikeDataSource likeDataSource;
    RatingCalculator ratingCalculator;

    @Inject
    GetFeedImpl(UserDataSource userDataSource, LikeDataSource likeDataSource, RatingCalculator ratingCalculator, HipsterMapper hipsterMapper) {
        this.userDataSource = userDataSource;
        this.likeDataSource = likeDataSource;
        this.ratingCalculator = ratingCalculator;
        this.hipsterMapper = hipsterMapper;
    }

    @Override
    public Observable<FeedBoundary> getFeed() {
        Observable<List<Hipster>> hipsterObservable = getHipsterObservable().cache();

        Observable<Map<String, Integer>> likerCountPerUser = hipsterObservable
                .map(getMapHipsterToIds()).mapMany(obtainLikesFromUserIds());

        Observable<FeedBoundary> zipObservable = Observable.zip(hipsterObservable, likerCountPerUser,
                new Func2<List<Hipster>, Map<String, Integer>, List<Hipster>>() {
                    @Override
                    public List<Hipster> call(List<Hipster> hipsters, Map<String, Integer> countLikers) {
                        for (Hipster hipster : hipsters) {
                            if (countLikers.containsKey(hipster.getUserId())) {

                                int totalLikes = countLikers.get(hipster.getUserId());
                                hipster.setNumLikes(totalLikes);
                            } else {
                                hipster.setNumLikes(0);
                            }
                        }

                        return hipsters;
                    }
                })
                .map(fillFeedBinder());

        return zipObservable;
    }

    private Func1<List<Hipster>, FeedBoundary> fillFeedBinder() {
        return new Func1<List<Hipster>, FeedBoundary>() {
            @Override
            public FeedBoundary call(List<Hipster> hipsters) {
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
                return feedBoundary;
            }
        };
    }

    private Func1<List<String>, Observable<Map<String, Integer>>> obtainLikesFromUserIds() {
        return new Func1<List<String>, Observable<Map<String, Integer>>>() {
            @Override
            public Observable<Map<String, Integer>> call(List<String> userIds) {
                return getCountLikersObservable(userIds);
            }
        };
    }

    private Func1<List<Hipster>, List<String>> getMapHipsterToIds() {
        return new Func1<List<Hipster>, List<String>>() {
            @Override
            public List<String> call(List<Hipster> hipsters) {
                List<String> usersIdsForLikers = new ArrayList<String>();

                for (Hipster hipster : hipsters) {
                    usersIdsForLikers.add(hipster.getUserId());
                }
                return usersIdsForLikers;
            }
        };
    }

    private Observable<List<Hipster>> getHipsterObservable() {
        return Observable.create(new Observable.OnSubscribeFunc<List<Hipster>>() {

            @Override
            public Subscription onSubscribe(final Observer<? super List<Hipster>> observer) {
                userDataSource.getUsers(new GetUserCallback() {
                    @Override
                    public void usersReady(List<Hipster> users) {
                        observer.onNext(users);
                        observer.onCompleted();
                    }
                });

                return Subscriptions.empty();
            }
        });
    }

    private Observable<Map<String, Integer>> getCountLikersObservable(final List<String> userIds) {
        return Observable.create(new Observable.OnSubscribeFunc<Map<String, Integer>>() {
            @Override
            public Subscription onSubscribe(final Observer<? super Map<String, Integer>> observer) {
                likeDataSource.getCountLikesForUser(userIds, new GetCountLikesCallback() {
                    @Override
                    public void countUsers(Map<String, Integer> countLikers) {
                        observer.onNext(countLikers);
                        observer.onCompleted();
                    }
                });

                return Subscriptions.empty();
            }
        });
    }
}
