package com.flipper83.protohipster.feed.domain.module;

import com.flipper83.protohipster.feed.datasource.interfaces.LikeDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.LikeUserCallback;
import com.flipper83.protohipster.feed.domain.interactors.LikeHipster;
import com.flipper83.protohipster.feed.view.viewmodel.callback.LikeHipsterCallback;

import javax.inject.Inject;

/**
 * Implementation for the interactor LikeHipster
 */
public class LikeHipsterImp implements LikeHipster {

    LikeDataSource likeDataSource;
    private LikeHipsterCallback likeHipsterCallback;


    @Inject
    public LikeHipsterImp(LikeDataSource likeDataSource) {
        this.likeDataSource = likeDataSource;
    }

    @Override
    public void like(final String userId, LikeHipsterCallback likeHipsterCallback) {
        this.likeHipsterCallback = likeHipsterCallback;
        likeDataSource.likeUser(userId, likeUserCallback);
    }

    private LikeUserCallback likeUserCallback = new LikeUserCallback() {
        @Override
        public void liked(String userId) {
            likeHipsterCallback.liked(userId);
        }
    };
}
