package com.xzjie.et.wechat.entity;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;

public class MessageNewData {

    private List<String> touser;
    private String msgtype;

    private ItemMap mpnews;

    private String send_ignore_reprint;

    private MessageNewData() {
        mpnews =new ItemMap();
    }

    public static MessageNewData builder() {
        return new MessageNewData();
    }

    public String build() {
        return JSON.toJSONString(this);
    }

    public MessageNewData addOpenId(String openId) {
        touser.add(openId);
        return this;
    }

    public MessageNewData addMediaId(String mediaId){
        mpnews.put("media_id",mediaId);
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

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public ItemMap getMpnews() {
        return mpnews;
    }

    public void setMpnews(ItemMap mpnews) {
        this.mpnews = mpnews;
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
