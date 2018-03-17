package com.xzjie.et.core.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by xzjie on 2017/9/11.
 */
public class RoleException extends AuthenticationException {
    public RoleException() {
        super();
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleException(String message) {
        super(message);
    }

    public RoleException(Throwable cause) {
        super(cause);
    }
}
