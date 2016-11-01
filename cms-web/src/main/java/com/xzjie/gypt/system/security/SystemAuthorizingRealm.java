package com.xzjie.gypt.system.security;

import java.io.Serializable;
import java.security.Permission;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.xzjie.gypt.common.security.CaptchaException;
import com.xzjie.gypt.common.security.UsernamePasswordCaptchaToken;
import com.xzjie.gypt.common.utils.constants.ConstantsUtils;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.service.AccountService;
import com.xzjie.gypt.system.service.OAuthService;

public class SystemAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountService accountService;
	@Autowired
	private OAuthService oauthService;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//String username = (String)principals.getPrimaryPrincipal();
		Principal principal = (Principal) getAvailablePrincipal(principals);
		// 添加用户权限
		//authorizationInfo.addStringPermission("user");
		
        authorizationInfo.setRoles(oauthService.getUserRoles(principal.orgId, principal.userId));
        authorizationInfo.setStringPermissions(oauthService.getPermissions(principal.orgId, principal.userId));
       
        return authorizationInfo;
	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;

		if (logger.isDebugEnabled()) {
			logger.debug("login submit user token: "+ JSON.toJSON(token));
		}

		String username = token.getUsername();
		String stype = token.getStype();
		boolean isMobile = token.isMobile();
		if (username == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}
		// 增加判断验证码逻辑
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
			throw new CaptchaException("User authentication code and verification code system is inconsistent. user captcha code [" +captcha+ "] ,system captcha code ["+exitCode+"]");
		}
 
		Account account = accountService.getAccountLogin(username,stype);

		if (null == account) {
			throw new UnknownAccountException("No account found for user [" + username + "]");
		}

		if (ConstantsUtils.ACCOUNT_LOCKED.equals(account.getLocked())) {
			// throw new AuthenticationException("该已帐号冻结,禁止登录.");
			throw new LockedAccountException("The sub-account frozen, banned from.");
		}

		return new SimpleAuthenticationInfo(new Principal(account, isMobile),
				account.getPassword(), ByteSource.Util.bytes(account.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);

	}
	
	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
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

		public Principal(Account account, boolean isMobile) {
			this.userId = account.getUserId();
			this.username = account.getName();
			this.nickName = account.getNickName();
			this.phone = account.getPhone();
			this.eMail = account.geteMail();
			this.orgId = account.getOrgId();
			this.isMobile = isMobile;
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

	}

}
