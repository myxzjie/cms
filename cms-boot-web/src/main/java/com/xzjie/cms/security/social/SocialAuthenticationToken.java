package com.xzjie.cms.security.social;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Vito
 * @since 2022/6/11 2:12 上午
 */
public class SocialAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    /**
     * 没登录之前,principal
     *
     * @param userId
     */
    public SocialAuthenticationToken(Long userId) {
        super((Collection) null);
        this.principal = userId;
        this.setAuthenticated(false);
    }

    /**
     * 登录认证之后,principal我们使用用户信息
     *
     * @param principal
     * @param authorities
     */
    public SocialAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }
}
