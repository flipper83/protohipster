package com.flipper83.protohipster.globalutils.cache;

import java.util.List;

/**
 * This is a contract for all cache implementations
 */
public interface Cache<T> {
    T get(String key);
    void put(String key, T value);
    List<T> getAllValues();
}
