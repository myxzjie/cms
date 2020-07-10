package com.xzjie.cms.core.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class I18Utils {

    private static MessageSource source;

    public static MessageSource getSource() {
        return source;
    }

    public static void setMessageSource(MessageSource messageSource) {
        source = messageSource;
    }

    public static String getMessage(Integer code) {
        return source.getMessage(code.toString(), null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(Integer code, String... params) {
        return source.getMessage(code.toString(), params, LocaleContextHolder.getLocale());
    }
}
