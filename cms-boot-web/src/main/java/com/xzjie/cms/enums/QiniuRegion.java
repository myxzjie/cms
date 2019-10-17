package com.xzjie.cms.enums;

import lombok.Getter;

@Getter
public enum QiniuRegion {
    auto("自由"),
    HUADONG("华东"),
    HUABEI("华北"),
    HUANAN("华南"),
    BEIMEI("北美"),
    XINJIAPO("东南亚");

    private String name;

    QiniuRegion(String name) {
        this.name = name;
    }
}
