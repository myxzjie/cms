package com.xzjie.cms.core.utils;

import com.xzjie.cms.security.SecurityUserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUtils {

    public static SecurityUserDetails getUserDetails() {
        try {
            SecurityUserDetails userDetails = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("登录状态过期", e);
        }
    }

    /**
     * 获取系统用户名称
     *
     * @return 系统用户名称
     */
    public static String getUsername() {
        try {
            return getUserDetails().getUsername();
        } catch (BadCredentialsException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long getUserId() {
        try {
            return getUserDetails().getUserId();
        } catch (BadCredentialsException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Set<String> getUserRoles() {
        return getUserDetails()
                .getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}
