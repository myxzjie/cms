package com.xzjie.cms.security.permission;

import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.enums.RoleType;
import com.xzjie.cms.security.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vito
 * @since 2022/3/23 12:58 上午
 */
@Component("permission")
public class CustomPermissionEvaluator { // implements PermissionEvaluator
    public boolean hasPermission(Object... permission) {
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        if (userDetails == null || CollectionUtils.isEmpty(userDetails.getAuthorities())) {
            return false;
        }

        // 获取当前用户的所有权限
        Set<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return authorities.contains(RoleType.ADMINISTRATOR.getCode()) || Arrays.stream(permission).anyMatch(authorities::contains);
    }
}
