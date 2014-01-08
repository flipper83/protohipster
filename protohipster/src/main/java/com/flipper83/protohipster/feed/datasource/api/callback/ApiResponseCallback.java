package com.flipper83.protohipster.feed.datasource.api.callback;

/**
 *
 */
public interface ApiResponseCallback<T> {
    void complete(T response);
}
