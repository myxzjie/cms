package com.xzjie.gypt.common.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @className CaptchaException
 * @description 验证码错误类
 * @author xzjie
 * @create 2016年6月3日22:59:31
 * @version V0.0.1 
 */
public class CaptchaException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CaptchaException() {

		super();

	}

	public CaptchaException(String message, Throwable cause) {

		super(message, cause);

	}

	public CaptchaException(String message) {

		super(message);

	}

	public CaptchaException(Throwable cause) {

		super(cause);

	}

}
