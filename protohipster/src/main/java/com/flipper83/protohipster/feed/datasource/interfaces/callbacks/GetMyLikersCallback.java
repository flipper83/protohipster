package com.flipper83.protohipster.feed.datasource.interfaces.callbacks;

import java.util.List;

/**
 * This is tha callback with the list of all users that has been liked for this user.
 */
public interface GetMyLikersCallback {
    void myLikers(List<String> userIds);
}
