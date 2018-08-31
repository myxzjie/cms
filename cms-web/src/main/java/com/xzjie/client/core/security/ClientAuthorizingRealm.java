package com.xzjie.client.core.security;

import com.alibaba.fastjson.JSON;
import com.xzjie.et.core.security.Principal;
import com.xzjie.et.core.security.RoleException;
import com.xzjie.et.core.security.UsernamePasswordCaptchaToken;
import com.xzjie.et.core.utils.ConstantsUtils;
import com.xzjie.et.system.model.Account;
import com.xzjie.et.system.model.Role;
import com.xzjie.et.system.service.AccountService;
import com.xzjie.et.system.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientAuthorizingRealm extends AuthorizingRealm {

    private final Logger LOG = LogManager.getLogger(getClass());


    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //principals.fromRealm(getName()).iterator().

        return authorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;

        if (LOG.isDebugEnabled()) {
            LOG.debug(">>>login submit user token: {}", JSON.toJSON(token));
        }

        String username = token.getUsername();
        //String stype = token.getStype();
        boolean isMobile = token.isMobile();
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        Account account = accountService.getAccountLogin(username);

        if (null == account) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }

        if (ConstantsUtils.ACCOUNTLOCKED.NO.getIndex() == account.getLocked()) {
            // throw new AuthenticationException("该已帐号冻结,禁止登录.");
            throw new LockedAccountException("The sub-account frozen, banned from.");
        }

        Role role = roleService.getRoleByUserId(account.getUserId());
        if (role == null) {
            throw new RoleException("获得用户角色为空");
        }

        return new SimpleAuthenticationInfo(new Principal(account, isMobile, role.getRoleId(), role.getRoleCode(), Long.parseLong(ConstantsUtils.SITE_ID_DEFAULT)),
                account.getPassword(), ByteSource.Util.bytes(account.getCredentialsSalt()), // salt=username+salt
                getName() // realm name
        );

    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        clearAllCache();
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }


}
