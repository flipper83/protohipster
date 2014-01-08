package com.flipper83.protohipster.feed.datasource.module.api;

import android.content.Context;

import com.flipper83.protohipster.feed.datasource.api.Api;
import com.flipper83.protohipster.feed.datasource.api.call.ApiCall;
import com.parse.Parse;

/**
 *
 */
public class ApiParse implements Api {


    public ApiParse(Context context){
        Parse.initialize(context, "qFiaaQwGeJCdIpp3WUiBeuk0FXC4vRe0wGImASrB", "L8W1fORdrPiOGl63XzlxgUziX8q9f0ylV5tdV2Y1");
    }

    @Override
    public void call(ApiCall apiCall) {
        apiCall.call();
    }
}
