package com.xzjie.et.core.security;

import java.io.Serializable;

import com.xzjie.et.core.utils.ConstantsUtils;
import com.xzjie.et.system.model.Role;
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

import com.alibaba.fastjson.JSON;
import com.xzjie.et.system.model.Account;
import com.xzjie.et.system.service.AccountService;

public class SystemAuthorizingRealm extends AuthorizingRealm {

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

    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private Long userId; // 用户ID
        private String username; // 登录名称
        private String nickName; // 昵称
        private String phone; // 手机号
        private String eMail; // 邮箱
        private Long orgId; //组织
        private boolean isMobile; // 是否手机登录
        private String stype;
        private String avatar;
        private Long roleId;
        private String roleCode;
        private Long siteId;

        private Principal(Account model, boolean isMobile) {
            this(model, isMobile, null, null, null);
        }

        public Principal(Account account, boolean isMobile, Long roleId, String roleCode, Long siteId) {
            this.userId = account.getUserId();
            this.username = account.getName();
            this.nickName = account.getNickName();
            this.phone = account.getPhone();
            this.eMail = account.geteMail();
            this.orgId = account.getOrgId();
            this.isMobile = isMobile;
            this.stype = account.getStype();
            this.avatar = account.getAvatar();
            this.roleId = roleId;
            this.roleCode = roleCode;
            this.siteId = siteId;
        }

        public Long getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }

        public String getNickName() {
            return nickName;
        }

        public String getPhone() {
            return phone;
        }

        public String geteMail() {
            return eMail;
        }

        public Long getOrgId() {
            return orgId;
        }

        public boolean isMobile() {
            return isMobile;
        }

        public String getStype() {
            return stype;
        }

        public void setStype(String stype) {
            this.stype = stype;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }

        public String getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(String roleCode) {
            this.roleCode = roleCode;
        }

        public Long getSiteId() {
            return siteId;
        }

        public void setSiteId(Long siteId) {
            this.siteId = siteId;
        }
    }
}
