package com.xzjie.et.wechat.entity;

import com.xzjie.et.wechat.model.WxMessage;

import java.io.Serializable;
import java.util.List;

public class WxArticleModel implements Serializable {

   private List<WxMessage> messages;

    public List<WxMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<WxMessage> messages) {
        this.messages = messages;
    }
}
