package com.flipper83.protohipster.feed.domain.module;

import com.flipper83.protohipster.feed.datasource.interfaces.LikeDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.LikeUserCallback;
import com.flipper83.protohipster.feed.domain.interactors.LikeHipster;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Implementation for the interactor LikeHipster
 */
public class LikeHipsterImp implements LikeHipster{

    LikeDataSource likeDataSource;

    @Inject
    public LikeHipsterImp(LikeDataSource likeDataSource){
        this.likeDataSource = likeDataSource;
    }

    @Override
    public Observable<String> like(final String userId) {
        return Observable.create(new Observable.OnSubscribeFunc<String>() {
            @Override
            public Subscription onSubscribe(final Observer<? super String> observer) {
                likeDataSource.likeUser(userId,new LikeUserCallback() {
                    @Override
                    public void liked(String userId) {
                        observer.onNext(userId);
                        observer.onCompleted();
                    }
                });
                return Subscriptions.empty();
            }
        });
    }
}
