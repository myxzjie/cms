package com.xzjie.et.wechat.entity;

import com.alibaba.fastjson.JSON;

public class SenderMessage {
    private String touser;
    private String msgtype;

    public String getTouser() {
        return touser;
    }
    public void setTouser(String touser) {
        this.touser = touser;
    }
    public String getMsgtype() {
        return msgtype;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String toJson(){
      return  JSON.toJSONString(this);
    }
}
