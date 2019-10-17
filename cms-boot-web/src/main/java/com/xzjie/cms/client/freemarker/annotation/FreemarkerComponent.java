package com.xzjie.cms.client.freemarker.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface FreemarkerComponent {
    String value() default "";
}
