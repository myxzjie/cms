package com.xzjie.et.wechat.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class WxAccessToken implements Serializable{

    private String access_token;

    private Long expires_in;

    private Timestamp timestamp;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
