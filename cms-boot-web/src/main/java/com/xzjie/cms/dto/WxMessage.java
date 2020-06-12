package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WxMessage implements Serializable {
    private List<String> touser = Lists.newArrayList();
    private String msgtype;

    private ItemMap text;

    private ItemMap mpnews;

    private String send_ignore_reprint;

    private WxMessage() {
    }

    public static WxMessage builder() {
        return new WxMessage();
    }

    public String build() {
        return JsonUtils.toJsonString(this);
    }

    public WxMessage addOpenId(String openId) {
        touser.add(openId);
        return this;
    }

    public WxMessage add(String content) {
        if (text == null) {
            text = new ItemMap();
        }
        text.put("content", content);
        return this;
    }

    public WxMessage addMediaId(String mediaId) {
        if (mpnews == null) {
            mpnews = new ItemMap();
        }
        mpnews.put("media_id", mediaId);
        return this;
    }

    public WxMessage setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }


    public List<String> getTouser() {
        return touser;
    }

    public void setTouser(List<String> touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }


    public ItemMap getText() {
        return text;
    }

    public String getSend_ignore_reprint() {
        return send_ignore_reprint;
    }

    public void setSend_ignore_reprint(String send_ignore_reprint) {
        this.send_ignore_reprint = send_ignore_reprint;
    }

    public class ItemMap extends HashMap<String, String> {

        public ItemMap() {
        }

        public ItemMap(String key, String value) {
            this.put(key, value);
        }
    }

}
