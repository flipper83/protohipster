package com.flipper83.protohipster.feed.domain.module;

import com.flipper83.protohipster.feed.datasource.api.call.parse.response.GetLikesResponse;
import com.flipper83.protohipster.feed.datasource.interfaces.LikeDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetMyLikersCallback;
import com.flipper83.protohipster.feed.domain.interactors.GetMyLikers;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * this is an implementation for feed
 */
class GetMyLikersImpl extends GetLikesResponse implements GetMyLikers {


    LikeDataSource likeDataSource;


    @Inject
    GetMyLikersImpl(LikeDataSource likeDataSource) {
        this.likeDataSource = likeDataSource;
    }

    @Override
    public Observable<List<String>> getMyLikers() {
        return Observable.create(new Observable.OnSubscribeFunc<List<String>>() {

            @Override
            public Subscription onSubscribe(final Observer<? super List<String>> observer) {
                likeDataSource.getMyLikers(new GetMyLikersCallback() {
                    @Override
                    public void myLikers(List<String> myLikers) {
                        observer.onNext(myLikers);
                        observer.onCompleted();
                    }
                });
                return Subscriptions.empty();
            }
        });
    }

}
