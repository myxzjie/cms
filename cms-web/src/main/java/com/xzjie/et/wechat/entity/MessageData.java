package com.xzjie.et.wechat.entity;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;

public class MessageData {

    private List<String> touser;
    private String msgtype;

    private ItemMap text;

    private MessageData() {
        text = new ItemMap();
    }

    public static MessageData builder() {
        return new MessageData();
    }

    public String build() {
        return JSON.toJSONString(this);
    }

    public MessageData addOpenId(String openId) {
        touser.add(openId);
        return this;
    }

    public MessageData add(String content) {
        text.put("content", content);
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

    public ItemMap getText() {
        return text;
    }

    public class ItemMap extends HashMap<String, String> {

        public ItemMap() {
        }

        public ItemMap(String key, String value) {
            this.put(key, value);
        }
    }
}
