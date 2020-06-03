package com.xzjie.cms.core.utils;

import java.util.LinkedHashMap;

public class MapUtils extends LinkedHashMap<String, Object> {

    public static MapUtils create() {
        return new MapUtils();
    }

    public static MapUtils success() {
        return create().set("code", 0);
    }

    public static MapUtils success(Object data) {
        return success().set("data", data);
    }

    public static MapUtils success(Object data, Long total) {
        return success(data).set("total", total);
    }

    public MapUtils set(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
