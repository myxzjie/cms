package com.xzjie.et.wechat.entity;


import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

public class MessageData {

    private List<String> touser;
    private String msgtype;

    private Map<String, Object> text;

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
    public MessageData add(String content){
        text.put("content",content);
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

    public Map<String, Object> getText() {
        return text;
    }

    public void setText(Map<String, Object> text) {
        this.text = text;
    }
}
