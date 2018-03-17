package com.xzjie.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by xzjie on 2017/9/12.
 */
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public static <T extends Object> T getBean(String name) {
        return (T) context.getBean(name);
    }

    public static <T extends Object> T getBean(Class<T> cls) {
        return (T) context.getBean(cls);
    }
}
