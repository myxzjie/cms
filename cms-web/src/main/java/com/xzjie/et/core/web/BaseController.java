package com.xzjie.et.core.web;

import com.xzjie.core.utils.DateUtils;
import com.xzjie.et.core.security.Principal;
import com.xzjie.et.core.utils.ConstantsUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

public abstract class BaseController {
	private final Logger LOG = LogManager.getLogger(BaseController.class);
	@Autowired
	protected HttpServletRequest request;
	@Value(value = "${template.admin}")
	private String template;
//	@Autowired
//	private RedisService redisService;
//
//	public RedisService getRedisService() {
//		return redisService;
//	}

	protected String getTemplate(){
		return template;
	}

	protected String getRemoteView(String view) {

//		Site site = (Site) redisService.get(ConstantsUtils.SITE_ID_KEY + getSiteId());
		String remote="";
//		if(StringUtils.isNotBlank(site.getTheme())){
//			remote = site.getTheme() + "/" + view;
//		}else{
		remote = template + "/" +view;
//		}
		return remote;
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

	/**
	 * 获得当前用户ID
	 * @return
	 */
	public Long getUserId() {
		Principal principal = getPrincipal();
		if (principal != null) {
			return principal.getUserId();
		}
		return null;
	}

	/**
	 * 获得当前用户名
	 * @return
	 */
	public String getUsername() {
		Principal principal = getPrincipal();
		if (principal != null) {
			return principal.getUsername();
		}
		return null;
	}

//	public Long getSiteId(){
//		SystemAuthorizingRealm.Principal principal = getPrincipal();
//		if (principal != null) {
//			return principal.getSiteId();
//		}
//		return null;
//
//	}

	public Long getSiteId() {
		String cid = request.getHeader(ConstantsUtils.ET_CID);
		
		if (cid == null) {
			cid = request.getParameter(ConstantsUtils.CID);
		}

		if (cid == null) {
			cid = ConstantsUtils.SITE_ID_DEFAULT;
		}
		return Long.parseLong(cid);

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
			LOG.error("获得session 错误:{}",e.getMessage());
		} catch (InvalidSessionException e) {
			LOG.error("获得session 错误:{}",e.getMessage());
		}
		return null;
	}

	/**
	 * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击>>>:" + System.currentTimeMillis());
		}
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				try {
					setValue(DateUtils.parseDate(text, DateUtils.parsePatterns));
				} catch (ParseException e) {
					//e.printStackTrace();
					LOG.error(">>Date 类型转换错误.{}",e.getMessage());
				}
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? DateUtils.formatDateTime((Date) value) : "";
			}
		});
	}
}
