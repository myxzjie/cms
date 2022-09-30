package com.xzjie.cms.wechat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xzjie.cms.core.utils.JsonUtils;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxAccessToken implements Serializable {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private int expiresIn = -1;

    public static WxAccessToken fromJson(String json) {
        return JsonUtils.toObject(json, WxAccessToken.class);
    }

    protected boolean canEqual(Object other) {
        return other instanceof WxAccessToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(canEqual(obj))) {
            return false;
        } else {
            WxAccessToken other = (WxAccessToken) obj;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object currentAccessToken = this.getAccessToken();
                Object otherAccessToken = other.getAccessToken();
                if (currentAccessToken == null) {
                    if (otherAccessToken == null) {
                        return this.getExpiresIn() == other.getExpiresIn();
                    }
                } else if (currentAccessToken.equals(otherAccessToken)) {
                    return this.getExpiresIn() == other.getExpiresIn();
                }

                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object currentAccessToken = this.getAccessToken();
        result = result * 59 + (currentAccessToken == null ? 43 : currentAccessToken.hashCode());
        result = result * 59 + this.getExpiresIn();
        return result;
    }


    @Override
    public String toString() {
        return "WxAccessToken(accessToken=" + this.getAccessToken() + ", expiresIn=" + this.getExpiresIn() + ")";
    }
}
