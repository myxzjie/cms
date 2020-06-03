package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

@Data
public class WxUserResult {
    private Integer subscribe;
    @JsonProperty("openid")
    private String openId;
    @JsonProperty("nickname")
    private String nickName;
    private Integer sex;
    private String language;
    private String city;
    private String province;
    private String country;
    @JsonProperty("headimgurl")
    private String avatar;
    @JsonProperty("subscribe_time")
    private Integer subscribeTime;
    @JsonProperty("unionid")
    private String unionId;
    private String remark;
    @JsonProperty("groupid")
    private Integer groupId;
    @JsonProperty("tagid_list")
    private Long[] tagIds;
    private String[] privileges;
    @JsonProperty("subscribe_scene")
    private String subscribeScene;
    @JsonProperty("qr_scene")
    private Integer qrScene;
    @JsonProperty("qr_scene_str")
    private String qrSceneStr;

    public static WxUserResult fromJson(String json) {
        return JsonUtils.toObject(json, WxUserResult.class);
    }
}
