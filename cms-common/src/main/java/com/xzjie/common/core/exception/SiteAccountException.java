package com.xzjie.common.core.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by xzjie on 2017/9/11.
 */
public class SiteAccountException extends AuthenticationException {
    public SiteAccountException() {
        super();
    }

    public SiteAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public SiteAccountException(String message) {
        super(message);
    }

    public SiteAccountException(Throwable cause) {
        super(cause);
    }
}
