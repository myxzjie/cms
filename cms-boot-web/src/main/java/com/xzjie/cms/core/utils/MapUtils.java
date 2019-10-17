package com.xzjie.cms.core.utils;

import java.util.LinkedHashMap;

public class MapUtils extends LinkedHashMap<String, Object> {

    public static MapUtils create() {
        return new MapUtils();
    }

    public MapUtils set(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
