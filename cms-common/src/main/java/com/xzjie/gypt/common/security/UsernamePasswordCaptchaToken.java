/**
 * radp-cms
 * @Title: UsernamePasswordCaptchaToken.java 
 * @Package org.radp.xzjie.core.security.shiro
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c)  2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月3日
 */
package com.xzjie.gypt.common.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @className UsernamePasswordCaptchaToken.java
 * @description TODO
 * @author xzjie
 * @create 2016年6月3日 下午11:18:36 
 * @version V0.0.1 
 */
public class UsernamePasswordCaptchaToken extends UsernamePasswordToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String captcha; //验证码
	private boolean isMobile; //是否手机端
	private String stype; //用户类型
	

	public UsernamePasswordCaptchaToken() {
		super();

	}
	
//	public UsernamePasswordCaptchaToken(String username, char[] password,
//			boolean rememberMe, String host, String captcha){
//		super(username, password, rememberMe, host);
//		this.captcha = captcha;
//	}

	public UsernamePasswordCaptchaToken(String username, char[] password,
			boolean rememberMe, String host, String captcha, String stype ,boolean isMobile) {
		
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.stype=stype;
		this.isMobile=isMobile;
	}
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}


	public boolean isMobile() {
		return isMobile;
	}

	public void setMobileLogin(boolean isMobile) {
		this.isMobile = isMobile;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

}
