package com.xzjie.cms.enums;

import lombok.Getter;

@Getter
public enum VerifyCodeScenes {

    EMAIL_CHANGE("更换邮箱");

    private String name;

    VerifyCodeScenes(String name) {
        this.name = name;
    }
}
