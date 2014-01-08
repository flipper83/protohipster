package com.flipper83.protohipster.feed.datasource.api.call.parse;

import com.flipper83.protohipster.feed.datasource.api.call.ApiCall;
import com.flipper83.protohipster.feed.datasource.api.callback.ApiResponseCallback;
import com.flipper83.protohipster.globalutils.module.LoggerProvider;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Like a user api call
 */
public class LikeUserCall extends ApiCall {

    private static final String LOGTAG = "LikeUserCall";
    private final String userId;

    public LikeUserCall(String userId, ApiResponseCallback<String> responseCallback) {
        super(responseCallback);
        this.userId = userId;
    }

    @Override
    public void call() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseLikeTableDefinitions.PARSE_LIKE_TABLE);
        query.whereEqualTo(ParseLikeTableDefinitions.PARSE_LIKE_USER_ID, userId);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseLikes, ParseException e) {
                if (e == null) {
                    if(parseLikes.size() > 0){
                        ParseObject likeObject = parseLikes.get(0);

                        likeObject.increment(ParseLikeTableDefinitions.PARSE_LIKE_NUM_LIKES);
                        likeObject.saveInBackground();
                    }

                    responseCallback.complete(userId);

                } else {
                    LoggerProvider.getLogger().d(LOGTAG, "Error retrying info from parse");
                }
            }

        });
    }
}
