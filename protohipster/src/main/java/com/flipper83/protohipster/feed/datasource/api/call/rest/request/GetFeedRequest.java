package com.flipper83.protohipster.feed.datasource.api.call.rest.request;

import com.flipper83.protohipster.feed.datasource.api.call.rest.GetFeedCall;
import com.flipper83.protohipster.feed.datasource.api.call.rest.response.GetFeedResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.Callback;

/**
 * The request for the feed
 */
public interface GetFeedRequest {
    public final static String SEED_HIPSTER = "hipster";

    /**
     * randomUser.me don't have page suppont, and the limit in one all is 5 users. I'm going to
     * simulate the page system with the seed param.
     */
    @GET("/")
    void getRandomUsers(@Query("results") int maxUsers,@Query("seed") int page,
                        Callback<GetFeedResponse> callback);

}
