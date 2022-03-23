package com.xzjie.cms.enums;

import lombok.Getter;

/**
 * @author Vito
 * @since 2022/3/23 11:08 下午
 */
@Getter
public enum RoleType {
    ADMINISTRATOR("administrator", "超级管理员"),
    ADMIN("admin", "管理员"),
    MEMBER("member", "普通会员");

    private String code;
    private String name;

    RoleType(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
