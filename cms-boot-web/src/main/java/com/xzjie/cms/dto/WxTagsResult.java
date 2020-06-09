package com.xzjie.cms.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WxTagsResult {
    private Long id;
    private String name;
    private Integer count;

    public static WxTagsResult fromJson(String json) {
        Map<String, WxTagsResult> map = JsonUtils.toObject(json, new TypeReference<Map<String, WxTagsResult>>() {
        });
        return map.get("tag");
    }

    public static List<WxTagsResult> fromJsonList(String json) {
        Map<String, List<WxTagsResult>> map = JsonUtils.toObject(json, new TypeReference<Map<String, List<WxTagsResult>>>() {
        });
        return map.get("tags");
    }

}
