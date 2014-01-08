package com.flipper83.protohipster.feed.datasource.interfaces.callbacks;

import java.util.Map;

/**
 * This is callback with the number of likes for an user.
 */
public interface GetCountLikesCallback {
    void countUsers(Map<String, Integer> countLikers);
}
