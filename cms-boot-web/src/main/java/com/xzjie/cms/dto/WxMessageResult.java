package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

@Data
public class WxMessageResult {
    @JsonProperty("errcode")
    private int code;

    @JsonProperty("errmsg")
    private String message;

    @JsonProperty("msg_id")
    private String messageId;

    @JsonProperty("msg_data_id")
    private String messageDataId;

    public static WxMessageResult fromJson(String json) {
        return JsonUtils.toObject(json, WxMessageResult.class);
    }
}
