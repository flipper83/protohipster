package com.flipper83.protohipster.feed.datasource.interfaces.callbacks;

/**
 * This is tha callback with the list of all users that has been liked for this user.
 */
public interface LikeUserCallback {
    void liked(String userId);
}
