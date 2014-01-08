package com.flipper83.protohipster.feed.datasource.interfaces;

import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetUserCallback;

/**
 * this data source provide hipsters
 */
public interface UserDataSource {
    void getUsers(GetUserCallback callback);
}
