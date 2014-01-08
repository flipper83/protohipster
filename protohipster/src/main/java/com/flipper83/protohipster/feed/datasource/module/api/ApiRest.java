package com.flipper83.protohipster.feed.datasource.module.api;

import com.flipper83.protohipster.feed.datasource.api.Api;
import com.flipper83.protohipster.feed.datasource.api.call.ApiCall;
import com.flipper83.protohipster.feed.datasource.api.call.rest.ApiRestCall;

import javax.inject.Inject;

import retrofit.RestAdapter;

/**
 *
 */
public class ApiRest implements Api {

    private final RestAdapter restAdapter;

    @Inject
    public ApiRest(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    @Override
    public void call(ApiCall apiCall) {
        ApiRestCall restCall = (ApiRestCall) apiCall;

        restCall.setRestAdapter(restAdapter);
        restCall.call();


    }

    public final static String DOMAIN = "http://api.randomuser.me";

}
