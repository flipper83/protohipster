package com.flipper83.protohipster.feed.domain.module;

import com.flipper83.protohipster.feed.datasource.api.call.parse.response.GetLikesResponse;
import com.flipper83.protohipster.feed.datasource.interfaces.LikeDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetMyLikersCallback;
import com.flipper83.protohipster.feed.domain.interactors.GetMyLikers;
import com.flipper83.protohipster.feed.view.viewmodel.callback.GetMyLikersUiCallback;

import java.util.List;

import javax.inject.Inject;

/**
 * this is an implementation for feed
 */
class GetMyLikersImpl extends GetLikesResponse implements GetMyLikers {


    LikeDataSource likeDataSource;
    private GetMyLikersUiCallback getMyLikersUiCallback;


    @Inject
    GetMyLikersImpl(LikeDataSource likeDataSource) {
        this.likeDataSource = likeDataSource;
    }

    @Override
    public void getMyLikers(GetMyLikersUiCallback getMyLikersUiCallback) {
        this.getMyLikersUiCallback = getMyLikersUiCallback;
        likeDataSource.getMyLikers(getMyLikersCallback);

    }

    private GetMyLikersCallback getMyLikersCallback = new GetMyLikersCallback() {
        @Override
        public void myLikers(List<String> userIds) {
            getMyLikersUiCallback.myLikersReady(userIds);
        }
    };

}
