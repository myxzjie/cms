package com.xzjie.cms.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

@Data
public class WxMediaUploadResult {
    private String url;
    private String type;
    @JsonProperty("media_id")
    private String mediaId;
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;
    @JsonProperty("created_at")
    private long createdAt;

    public static WxMediaUploadResult fromJson(String json) {
        return JsonUtils.toObject(json, WxMediaUploadResult.class);
    }
}
