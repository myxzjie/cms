package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

import java.util.Set;

@Data
public class WxTagging {
    @JsonProperty("tagid")
    private Long tagId;

    @JsonProperty("openid_list")
    private Set<String> openIds = Sets.newHashSet();

    public static WxTagging builder() {
        return new WxTagging();
    }

    public String build() {
        return JsonUtils.toJsonString(this);
    }


    public WxTagging setTagId(Long tagId) {
        this.tagId = tagId;
        return this;
    }

    public WxTagging setOpenId(String openId) {
        openIds.add(openId);
        return this;
    }
}
