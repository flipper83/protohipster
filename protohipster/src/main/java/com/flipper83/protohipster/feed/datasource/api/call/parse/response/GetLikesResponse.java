package com.flipper83.protohipster.feed.datasource.api.call.parse.response;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class GetLikesResponse {
    private static final int NO_EXIST = 0;

    Map<String, Integer> likes = new HashMap<String, Integer>();

    public void add(String userId, int numLikes) {
        likes.put(userId, numLikes);
    }

    public int getNumLikes(String userId) {
        if (likes.containsKey(userId)) {
            return likes.get(userId);
        } else {
            return NO_EXIST;
        }
    }

    public Map<String, Integer> getAllLikers() {
        return Collections.unmodifiableMap(likes);
    }
}
