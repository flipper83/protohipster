package com.flipper83.protohipster.feed.datasource.interfaces.callbacks;

import com.flipper83.protohipster.feed.domain.gateway.Hipster;

import java.util.List;

/**
 * This is the callback with all users that has been requested.
 */
public interface GetUserCallback {
    public void usersReady(List<Hipster> users);
}
