package com.flipper83.protohipster.globalutils.cache.implementations;


import com.flipper83.protohipster.globalutils.cache.Cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MapKeysCache implements Cache<String> {

    private Map<String,String> values = new HashMap<String, String>();


    @Override
    public String get(String key) {
        return values.get(key);
    }

    @Override
    public void put(String key, String value) {
        values.put(key,value);
    }

    @Override
    public List<String> getAllValues() {
        return new ArrayList<String>(values.values());
    }
}
