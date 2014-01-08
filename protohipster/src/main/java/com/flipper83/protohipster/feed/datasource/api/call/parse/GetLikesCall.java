package com.flipper83.protohipster.feed.datasource.api.call.parse;

import com.flipper83.protohipster.feed.datasource.api.call.ApiCall;
import com.flipper83.protohipster.feed.datasource.api.call.parse.response.GetLikesResponse;
import com.flipper83.protohipster.feed.datasource.api.callback.ApiResponseCallback;
import com.flipper83.protohipster.globalutils.module.LoggerProvider;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Obtain the number of likes for the users
 */
public class GetLikesCall extends ApiCall {


    private static final String LOGTAG = "GetLikesCall";
    private final List<String> userIds;

    public GetLikesCall(List<String> userIds, ApiResponseCallback<GetLikesResponse> responseCallback) {
        super(responseCallback);

        this.userIds = userIds;
    }

    @Override
    public void call() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseLikeTableDefinitions.PARSE_LIKE_TABLE);
        query.whereContainedIn(ParseLikeTableDefinitions.PARSE_LIKE_USER_ID, userIds);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseLikes, ParseException e) {
                if (e == null) {
                    GetLikesResponse response = new GetLikesResponse();

                    for (ParseObject likeObject : parseLikes) {
                        String userId = likeObject.getString(ParseLikeTableDefinitions.PARSE_LIKE_USER_ID);
                        int numLikes = likeObject.getInt(ParseLikeTableDefinitions.PARSE_LIKE_NUM_LIKES);

                        response.add(userId, numLikes);
                    }

                    responseCallback.complete(response);

                } else {
                    LoggerProvider.getLogger().d(LOGTAG, "Error retrying info from parse");
                }
            }

        });
    }
}
