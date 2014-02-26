package com.flipper83.protohipster.feed.datasource.api.call.rest;

import com.flipper83.protohipster.feed.datasource.api.call.rest.request.GetFeedRequest;
import com.flipper83.protohipster.feed.datasource.api.call.rest.response.GetFeedResponse;
import com.flipper83.protohipster.feed.datasource.api.callback.ApiResponseCallback;
import com.flipper83.protohipster.feed.datasource.api.model.UserApiEntry;
import com.flipper83.protohipster.globalutils.module.LoggerProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Api Call to request
 */
public class GetFeedCall extends ApiRestCall implements Callback<GetFeedResponse> {
    private static final int MAX_USERS = 5;
    private static final String LOGTAG = "GetFeedCall";
    private List<UserApiEntry> responseBuffer = new ArrayList<UserApiEntry>();
    private GetFeedRequest request;
    private int page;

    public GetFeedCall(ApiResponseCallback<List<UserApiEntry>> apiResponseCallback) {
        super(apiResponseCallback);
    }

    @Override
    public void call(RestAdapter restAdapter) {
        page = 0;
        responseBuffer.clear();

        request = restAdapter.create(GetFeedRequest.class);
        request.getRandomUsers(MAX_USERS, page, this);
    }

    @Override
    public void success(GetFeedResponse apiUsers, Response response) {
        responseBuffer.addAll(apiUsers.getResults());
        if (responseBuffer.size() >= MAX_USERS) {
            super.responseCallback.complete(responseBuffer);
        } else {
            page++;
            request.getRandomUsers(MAX_USERS, page, this);
        }
    }

    @Override
    public void failure(RetrofitError retrofitError) {
        //TODO notify api error.
        LoggerProvider.getLogger().d(LOGTAG, "an error happends " + retrofitError.getBody());
    }
}
