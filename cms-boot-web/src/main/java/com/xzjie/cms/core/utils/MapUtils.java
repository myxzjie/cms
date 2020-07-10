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

    public static MapUtils error(Integer code) {
        return error(code, I18Utils.getMessage(code));
    }

    public static MapUtils error(Integer code, String... params) {
        return error(code, I18Utils.getMessage(code, params));
    }

    public static MapUtils error(String message) {
        return error(99999, message);
    }

    public static MapUtils error(Integer code, String message) {
        return create().set("code", code).set("message", message);
    }


    public MapUtils set(String key, Object value) {
        this.put(key, value);
        return this;
    }
}
