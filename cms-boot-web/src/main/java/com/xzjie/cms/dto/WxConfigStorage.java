package com.xzjie.cms.dto;

import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

@Data
public class WxConfigStorage {

    private String appId;
    private String secret;
    private String token;
    private String templateId;
    private String aesKey;

    public static WxConfigStorage fromJson(String json) {
        return JsonUtils.toObject(json, WxConfigStorage.class);
    }

}
