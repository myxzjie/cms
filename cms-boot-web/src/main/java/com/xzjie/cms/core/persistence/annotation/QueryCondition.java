package com.xzjie.cms.core.persistence.annotation;

import com.xzjie.cms.core.persistence.enums.ConditionType;
import com.xzjie.cms.core.persistence.enums.Join;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface QueryCondition {
    String value() default "";

    String[] blurry() default {};

    ConditionType connect() default ConditionType.EQUAL;

    // join

    /**
     * join查询
     * @return
     */
    Join join() default Join.INNER;

    /**
     * join表名对象
     * @return
     */
    String table() default "";
}
