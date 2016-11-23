package com.xzjie.gypt.cms.web.front.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xzjie.gypt.common.security.CaptchaException;
import com.xzjie.gypt.common.security.UsernamePasswordCaptchaToken;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.StringUtils;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.web.controller.BaseController;

@Controller("${web.frontPath}LoginController")
public class LoginController extends BaseController{

	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping(value = "${web.frontPath}/login")
	public String login(){
		return "front/login";
	}
	
	@RequestMapping(value="${web.frontPath}/register")
	public String register(){
		return "front/register";
	}
	
	/**
	 * 前端登录
	 * @param verifycode
	 * @param username
	 * @param rememberMe
	 * @param mobileLogin
	 * @param account
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "${web.frontPath}/auth", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> auth(String verifycode, String username,
			Boolean rememberMe, Boolean mobileLogin, Account account,HttpServletRequest request) {
//		//登录用户名类型  用户名登录 1, 手机登录2, 邮箱登录3
//		String longUsernameType=ConstantsUtils.LOGIN_USERNAME_TYPE_NAME;
		logger.debug(">>"+verifycode);
		logger.debug(">>"+username);
		if (account == null){
			return MapResult.mapError(RspCode.R10001,"对不起,您输入用户名和密码");
		}
		account.setName(username);
		logger.debug(">>"+JSON.toJSONString(account));
		if(StringUtils.isBlank(account.getName())){
			return MapResult.mapError(RspCode.R10003,"对不起，您输入的用户名为空，请重新输入");
		}
		
		if(StringUtils.isBlank(account.getPassword())){
			return MapResult.mapError(RspCode.R10004,"对不起，您输入的密码为空，请重新输入");
		}
		
		if(StringUtils.isBlank(verifycode)){
			return MapResult.mapError(RspCode.R10010,"对不起，您输入的验证码为空，请重新输入");
		}
		
		//String username= account.getName();
		char[] password=account.getPassword().toCharArray();//DigestUtils.md5Hex(account.getPassword()).toCharArray();
		String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
		String captcha=verifycode;
		String stype=account.getStype();
		rememberMe =rememberMe==null?false:rememberMe;
		mobileLogin=mobileLogin==null?false:mobileLogin;
		
		UsernamePasswordCaptchaToken token=new UsernamePasswordCaptchaToken(username, password, rememberMe, host, captcha,stype, mobileLogin);
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
			 
			return MapResult.mapOK(RspCode.R00000);
        } catch (UnknownAccountException e) {
            logger.error("账号不存在："+ e.getMessage());
            return MapResult.mapError(RspCode.R10000,"对不起，您账号不存在");
        } catch (LockedAccountException e) {
            logger.error("账号未启用："+ e.getMessage());
            return MapResult.mapError(RspCode.R10006,"对不起，您账号被冻结");
        } catch (IncorrectCredentialsException e) {
            logger.error("密码错误："+ e.getMessage());
            return MapResult.mapError(RspCode.R10001,"对不起，您账号或密码错误");
        } catch (CaptchaException e) {
        	logger.error("验证码错误："+ e.getMessage());
        	return MapResult.mapError(RspCode.R10001,"对不起，验证码错误");
        } catch(ExcessiveAttemptsException e){
        	logger.error("重复登录错误超过次数："+ e.getMessage());
        	return MapResult.mapError(RspCode.R10001,"对不起，您重复登录错误超过5次,请等待 30分钟");
        } catch (RuntimeException e) {
            logger.error("未知错误,请联系管理员："+ e.getMessage());
            return MapResult.mapError(RspCode.R10001,"対不起，业务繁忙.");
        } 
		
	}

	/**
	 * 前端退出
	 * @return
	 */
	@RequestMapping(value = "${web.frontPath}/out", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> out(){
		SecurityUtils.getSubject().logout();
		return MapResult.mapOK(RspCode.R00000);
	}
}
