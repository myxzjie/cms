package com.xzjie.cms.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

import java.util.HashMap;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WxMessagePreview {

    private String touser;

    private String towxname;

    private String msgtype;

    private ItemMap text = new ItemMap();

    private ItemMap mpnews = new ItemMap();

//    private String send_ignore_reprint;

    private WxMessagePreview() {
    }

    public static WxMessagePreview builder() {
        return new WxMessagePreview();
    }

    public String build() {
        return JsonUtils.toJsonString(this);
    }

    public WxMessagePreview add(String content) {
        text.put("content", content);
        return this;
    }

    public WxMessagePreview addMediaId(String mediaId) {
        mpnews.put("media_id", mediaId);
        return this;
    }

    public class ItemMap extends HashMap<String, String> {

        public ItemMap() {
        }

        public ItemMap(String key, String value) {
            this.put(key, value);
        }
    }
}
