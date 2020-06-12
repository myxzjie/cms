package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class WxMessageTag implements Serializable {
    private Filter filter = new Filter();
    private String msgtype;

    private ItemMap text;

    private ItemMap mpnews;

    @JsonProperty("send_ignore_reprint")
    private Integer sendIgnoreReprint = 0;

    private WxMessageTag() {
    }

    public static WxMessageTag builder() {
        return new WxMessageTag();
    }

    public String build() {
        return JsonUtils.toJsonString(this);
    }

    public WxMessageTag setAll(boolean all) {
        filter.setAll(all);
        return this;
    }

    public WxMessageTag setTagId(Long tagId) {
        filter.setTagId(tagId);
        return this;
    }

    public WxMessageTag add(String content) {
        if (text == null) {
            text = new ItemMap();
        }
        text.put("content", content);
        return this;
    }

    public WxMessageTag addMediaId(String mediaId) {
        if (mpnews == null) {
            mpnews = new ItemMap();
        }
        mpnews.put("media_id", mediaId);
        return this;
    }

    public WxMessageTag setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }

    public WxMessageTag setSendIgnoreReprint(Integer sendIgnoreReprint) {
        this.sendIgnoreReprint = sendIgnoreReprint;
        return this;
    }

    public class ItemMap extends HashMap<String, String> {

        public ItemMap() {
        }

        public ItemMap(String key, String value) {
            this.put(key, value);
        }
    }

    @Data
    private class Filter {
        @JsonProperty("is_to_all")
        private boolean all;
        @JsonProperty("tag_id")
        private Long tagId;
    }

}
