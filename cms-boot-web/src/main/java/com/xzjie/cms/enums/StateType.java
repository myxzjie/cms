package com.xzjie.cms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Vito
 * @since 2022/3/6 11:55 下午
 */
@Getter
@AllArgsConstructor
public enum StateType {
    INVALID(0, "失效"),
    NORMAL(1, "正常");

    private Integer code;
    private String name;
}
