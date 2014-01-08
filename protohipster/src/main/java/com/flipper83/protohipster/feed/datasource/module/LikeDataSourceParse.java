package com.flipper83.protohipster.feed.datasource.module;

import com.flipper83.protohipster.feed.datasource.api.Api;
import com.flipper83.protohipster.feed.datasource.api.call.parse.GetLikesCall;
import com.flipper83.protohipster.feed.datasource.api.call.parse.LikeUserCall;
import com.flipper83.protohipster.feed.datasource.api.call.parse.response.GetLikesResponse;
import com.flipper83.protohipster.feed.datasource.api.callback.ApiResponseCallback;
import com.flipper83.protohipster.feed.datasource.interfaces.LikeDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetCountLikesCallback;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetMyLikersCallback;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.LikeUserCallback;
import com.flipper83.protohipster.globalutils.cache.Cache;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * implementation for UserDataSource
 */
class LikeDataSourceParse implements LikeDataSource {


    Api api;
    Cache myLikers;
    private GetCountLikesCallback getCountLikesCallback;
    private LikeUserCallback likeUserCallback;


    @Inject
    LikeDataSourceParse(@Named("ApiParse") Api api,@Named("MapKeysCache") Cache myLikers) {
        this.api = api;
        this.myLikers = myLikers;
    }

    @Override
    public void getCountLikesForUser(List<String> userIds,
                                     GetCountLikesCallback getCountLikesCallback) {
        this.getCountLikesCallback = getCountLikesCallback;

        api.call(new GetLikesCall(userIds, responseGetLikesCall));
    }

    @Override
    public void getMyLikers(GetMyLikersCallback getMyLikersCallback) {
        getMyLikersCallback.myLikers(myLikers.getAllValues());
    }

    @Override
    public void likeUser(String userId, LikeUserCallback likeUserCallback) {
        myLikers.put(userId,userId);
        this.likeUserCallback = likeUserCallback;
        api.call(new LikeUserCall(userId,responseLikeUserCall));
    }

    private ApiResponseCallback<String> responseLikeUserCall = new ApiResponseCallback<String>() {
        @Override
        public void complete(String response) {
            String userId = response;
            likeUserCallback.liked(userId);
        }
    };

    ApiResponseCallback<GetLikesResponse> responseGetLikesCall =
            new ApiResponseCallback<GetLikesResponse>() {
        @Override
        public void complete(GetLikesResponse response) {
            Map<String, Integer> allCountLikers = response.getAllLikers();
            getCountLikesCallback.countUsers(allCountLikers);
        }
    };
}
