package com.xzjie.cms.wechat.dto;

import com.google.common.collect.Lists;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

import java.util.List;

@Data
public class WxOpenIdResult {
    private Long total;
    private Integer count;
    private String nextOpenid;
    private Openids data;

    public static WxOpenIdResult fromJson(String json) {
        return JsonUtils.toObject(json, WxOpenIdResult.class);
    }

    public List<String> getOpenids() {
        return data.getOpenid();
    }

    @Data
    private class Openids {
        private List<String> openid = Lists.newArrayList();
    }
}
