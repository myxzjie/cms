package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

@Data
public class WxMpError {

    @JsonProperty("errcode")
    private int code;
    @JsonProperty("errmsg")
    private String message;
    private String errorMsgEn;
    private String json;

    public static WxMpError fromJson(String json) {
        return JsonUtils.toObject(json, WxMpError.class);
    }


    @Override
    public String toString() {
        return this.json == null ? "错误代码：" + this.code + ", 错误信息：" + this.message : "错误代码：" + this.code + ", 错误信息：" + this.message + "，微信原始报文：" + this.json;
    }
}
