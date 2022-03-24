package com.xzjie.cms.security;

import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.Menu;
import com.xzjie.cms.model.Role;
import com.xzjie.cms.service.AccountService;
import com.xzjie.cms.service.MenuService;
import com.xzjie.cms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @Override
    public SecurityUserDetails loadUserByUsername(String s) {
        Account account = accountService.getAccountByName(s);

        log.info(">> account: {}", account);

        return getSecurityUserDetails(account);
    }

    private SecurityUserDetails getSecurityUserDetails(Account model) {
        return new SecurityUserDetails(model.getUserId(), model.getName(), model.getNickName(), model.getPhone(), model.getEmail(), model.getAvatar(), model.getPassword(), model.getLocked() > 0, true, true, model.getLocked() > 0, getAuthorities(model.getUserId()));
    }


    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param userId
     * @return
     */
    protected Set<GrantedAuthority> getAuthorities(Long userId) {
        List<Role> roles = roleService.getRoleByUserId(userId);
        log.info("userId:{},roles:{}", userId, roles);
        if (roles == null) {
            throw new AccessDeniedException("角色权限错误");
        }
        Set<String> permissions = roles.stream().map(Role::getRoleCode).collect(Collectors.toSet());

        List<Integer> types = Arrays.asList(1, 2);
        List<Menu> menus = menuService.getMenuByRoles(permissions, types);

        permissions.addAll(menus.stream().filter(menu -> StringUtils.hasText(menu.getPermission())).map(Menu::getPermission).collect(Collectors.toSet()));

        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
