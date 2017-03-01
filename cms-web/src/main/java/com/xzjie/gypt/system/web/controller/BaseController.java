/**
 * radp-cms
 * @Title: BaseController.java 
 * @Package org.radp.xzjie.module.common.web
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年4月25日
 */
package com.xzjie.gypt.system.web.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzjie.cache.SystemCacheManager;
import com.xzjie.gypt.cms.model.Site;
import com.xzjie.gypt.common.utils.constants.ConstantsUtils;
import com.xzjie.gypt.system.model.Role;
import com.xzjie.gypt.system.security.SystemAuthorizingRealm.Principal;
import com.xzjie.gypt.system.service.RoleService;
import com.xzjie.gypt.wechat.model.WXAccount;
import com.xzjie.gypt.wechat.service.WXAccountService;

/**
 * @className BaseController
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年4月25日 下午4:01:30
 * @version V0.0.1
 */
public class BaseController extends com.xzjie.gypt.common.web.controller.BaseController {

	private final String SITE_KEY="site";
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private WXAccountService wxAccountService;
	
	@Autowired
	private SystemCacheManager systemCache;

	public String getRoleCode() {
		return getRole().getRoleCode();
	}
	
	
	public Long getWXAppid(){
		return getWXAccount().getWxAccId();
	}
	
	public WXAccount getWXAccount(){
		if (request.getSession().getAttribute(ConstantsUtils.SESSION_WECHAT_ACCESS) == null) {
			WXAccount model = wxAccountService.getWxAccountByUserId(getUserId());
			request.getSession().setAttribute(ConstantsUtils.SESSION_WECHAT_ACCESS, model);
		}
		WXAccount wXAccount = (WXAccount) request.getSession().getAttribute(ConstantsUtils.SESSION_WECHAT_ACCESS);
		return wXAccount;
	}

	public Role getRole() {
		Principal principal = getPrincipal();

		if (request.getSession().getAttribute(ConstantsUtils.SESSION_USER_ROEL) == null) {
			List<Role> role = roleService.getUserRoles(principal.getOrgId(), principal.getUserId());
			// 目前用户-角色 一对一
			request.getSession().setAttribute(ConstantsUtils.SESSION_USER_ROEL, role.get(0));
		}
		Role role = (Role) request.getSession().getAttribute(ConstantsUtils.SESSION_USER_ROEL);

		return role;

	}

	public Long getUserId() {
		Principal principal = getPrincipal();
		if (principal != null) {
			return principal.getUserId();
		}
		return null;
	}

	/**
	 * 获取当前登录者对象
	 */
	public Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	public Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	/**
	 * 获取授权主要对象
	 */
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	public Long getSiteId(){
		Site site = (Site) systemCache.get(SITE_KEY);
		return site.getSiteId();
	}
	

}
