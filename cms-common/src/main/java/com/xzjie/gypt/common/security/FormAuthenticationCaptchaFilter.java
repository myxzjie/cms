package com.xzjie.gypt.common.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * @className FormAuthenticationCaptchaFilter.java
 * @description TODO
 * @author xzjie
 * @create 2016年6月3日 下午11:11:50 
 * @version V0.0.1 
 */
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	public static final String DEFAULT_STYPE_PARAM="stype";
	public static final String DEFAULT_IS_MOBILE_PARAM="isMobile";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String stypeParam = DEFAULT_STYPE_PARAM;
	private String isMobileParam = DEFAULT_IS_MOBILE_PARAM;


	/**
	 * 获得验证码
	 * @param request
	 * @return
	 */
	protected String getCaptcha(ServletRequest request) {

		return WebUtils.getCleanParam(request, getCaptchaParam());

	}
	
	/**
	 * 获得用户类型
	 * @param request
	 * @return
	 */
	protected String getStype(ServletRequest request){
		return WebUtils.getCleanParam(request, getStypeParam());
	}
	
	/**
	 * 获得是否手机端
	 * @param request
	 * @return
	 */
	protected boolean isMobile(ServletRequest request){
		 return WebUtils.isTrue(request, getRememberMeParam());
	}
	
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {

		String username = getUsername(request);

		String password = getPassword(request);

		String captcha = getCaptcha(request);
		
		String stype = getStype(request);
		
		boolean isMobile = isMobile(request);
		
		boolean rememberMe = isRememberMe(request);

		String host = getHost(request);

		return new UsernamePasswordCaptchaToken(username,password==null?null:password.toCharArray(), rememberMe, host, captcha,stype,isMobile);

	}
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return super.onLoginSuccess(token, subject, request, response);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,ServletResponse response) {
		//return super.onLoginFailure(token, e, request, response);
		
		request.setAttribute(getFailureKeyAttribute(), e.getClass().getName());
        request.setAttribute("message", e.getMessage());
        return true;
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	public String getStypeParam() {
		return stypeParam;
	}

	public void setStypeParam(String stypeParam) {
		this.stypeParam = stypeParam;
	}

	public String getIsMobileParam() {
		return isMobileParam;
	}

	public void setIsMobileParam(String isMobileParam) {
		this.isMobileParam = isMobileParam;
	}

}
