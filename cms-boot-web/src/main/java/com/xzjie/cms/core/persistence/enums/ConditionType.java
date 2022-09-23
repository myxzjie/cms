package com.xzjie.cms.core.persistence.enums;

public enum ConditionType {
    EQUAL,
    // 下面四个用于Number类型的比较

    GT,

    GE,

    LT,

    LE,

    NOT_EQUAL,

    LIKE,

    NOT_LIKE,
    // 下面四个用于可比较类型(Comparable)的比较

    GREATER_THAN,

    GREATER_THAN_OR_EQUAL_TO,

    LESS_THAN,

    LESS_THAN_OR_EQUAL_TO
}
