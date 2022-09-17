package com.xzjie.cms.enums;

import lombok.Getter;

@Getter
public enum VerifyCodeScenes {

    EMAIL_CHANGE("更换邮箱"),
    BINDER("绑定第三方登录验证"),

    RESET_PASSWORD("重置密码");

    private String name;

    VerifyCodeScenes(String name) {
        this.name = name;
    }
}
