package com.xzjie.gypt.system.web.controller;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xzjie.gypt.common.security.CaptchaException;
import com.xzjie.gypt.common.security.FormAuthenticationCaptchaFilter;
import com.xzjie.gypt.system.model.Account;

@Controller
public class LoginController extends BaseController{

	/*@Autowired
	private AccountServiceI accountService;*/
	
	@RequestMapping(value = "login")
    public String login(HttpServletRequest req, Model model) {
        return "login";
    }
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public String showLoginForm(Account account,HttpServletRequest request, Model model) {
		String error = null;
        String exceptionClassName = (String)request.getAttribute(FormAuthenticationCaptchaFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        
        if(AccountException.class.getName().equals(exceptionClassName)){
        	error = "对不起，您输入用户名和密码";
        }else if(UnknownAccountException.class.getName().equals(exceptionClassName)||IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "对不起，您输入用户名/密码错误";
        }  else if(CaptchaException.class.getName().equals(exceptionClassName)) {
        	error="对不起，您输入验证码错误";
        } else if(LockedAccountException.class.getName().equals(exceptionClassName)) {
        	error="对不起，您账号被冻结,请联系管理员";
        } else if(exceptionClassName != null) {
            error = "未知错误:," + (String)request.getAttribute("message");
        }
        
        model.addAttribute("error",  error);
        
        return "login";//redirect:
    }
	
//	@RequestMapping(value = "auth", method = RequestMethod.POST)
//	@ResponseBody
//	public Map<String, Object> auth(String verifycode,Boolean rememberMe, Boolean mobileLogin, Account account,HttpServletRequest request) {
//		//登录用户名类型  用户名登录 1, 手机登录2, 邮箱登录3
//		String longUsernameType=ConstantsUtils.LOGIN_USERNAME_TYPE_NAME;
//		
//		if (account == null){
//			return MapResult.mapError(RspCode.R10001,"对不起,您输入用户名和密码");
//		}
//		
//		if(StringUtils.isBlank(account.getName())){
//			return MapResult.mapError(RspCode.R10003,"对不起，您输入的用户名为空，请重新输入");
//		}
//		
//		if(StringUtils.isBlank(account.getPassword())){
//			return MapResult.mapError(RspCode.R10004,"对不起，您输入的密码为空，请重新输入");
//		}
//		
//		if(StringUtils.isBlank(verifycode)){
//			return MapResult.mapError(RspCode.R10010,"对不起，您输入的验证码为空，请重新输入");
//		}
//		
//		//用户登录
//		if(RegexUtils.checkUserName(account.getName())){
//			longUsernameType=ConstantsUtils.LOGIN_USERNAME_TYPE_NAME;
//			//entity =accountService.getAccount(account.getName(), "", "", account.getStype());
//		}else if(RegexUtils.checkMobile(account.getName())){//手机登录
//			longUsernameType=ConstantsUtils.LOGIN_USERNAME_TYPE_MOBILE;
//			//entity =accountService.getAccount("", account.getName(), "", account.getStype());
//		}else if(RegexUtils.checkEmail(account.getName())){//邮箱登录
//			longUsernameType=ConstantsUtils.LOGIN_USERNAME_TYPE_EMAIL;
//			//entity =accountService.getAccount("", "", account.getName(), account.getStype());
//		}else{
//			return MapResult.mapError(RspCode.R10008,"对不起，您的用户名不合法");
//		}
//		
//		/*Account entity=null;
//		
//		HttpSession session = request.getSession();
//		
//		
//		
//		//判断空
//		
//		
//		
//		
//		//验证码
//		String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
//		if(!code.equals(verifycode)){
//			return MapResult.mapError(RspCode.R10010,"对不起，您输入的验证码错误，请重新输入");
//		}
//		
//		//权限 操作人员
//		if(StringUtils.isBlank(account.getStype())||!ConstantsUtils.ACCOUNT_STYPE_OPERATOR.equals(account.getStype())){
//			return MapResult.mapError(RspCode.R10002,"对不起，您没有权限登录");
//		}
//		
//		
//		
//		if(entity==null){
//			return MapResult.mapError(RspCode.R10000,"对不起，您账号不存在");
//		}
//		//获得加密密码
//		String edPassword=EncryptDecode.getPasswordEncrypt(account.getPassword());
//		
//		if(ConstantsUtils.LOGIN_TYPE_NAME.equals(loginType)){
//			if(!account.getName().equals(entity.getName())||!edPassword.equals(entity.getPassword())){
//				return MapResult.mapError(RspCode.R10001,"对不起，您手机号或密码错误");
//			}
//		}else if(ConstantsUtils.LOGIN_TYPE_MOBILE.equals(loginType)){//手机登录
//			if(!account.getName().equals(entity.getPhone())||!edPassword.equals(entity.getPassword())){
//				return MapResult.mapError(RspCode.R10001,"对不起，您手机号或密码错误");
//			}
//		}else if(ConstantsUtils.LOGIN_TYPE_EMAIL.equals(loginType)){//邮箱登录
//			if(!account.getName().equals(entity.geteMail())||!edPassword.equals(entity.getPassword())){
//				return MapResult.mapError(RspCode.R10001,"对不起，您邮箱或密码错误");
//			}
//		}else{
//			return MapResult.mapError(RspCode.R10008,"对不起，您的用户名不合法");
//		}
//		
//		
//		if(ConstantsUtils.ACCOUNT_FREEZE.equals(entity.getFreeze())){
//			return MapResult.mapError(RspCode.R10006,"对不起，您用户名被冻结");
//		}
//		
//		if(!account.getStype().equals(entity.getStype())){
//			return MapResult.mapError(RspCode.R10005,"对不起，您没有权限登录");
//		}
//		entity.setPassword("");
//		request.getSession().removeAttribute(ConstantsUtils.SESSION_USER);
//		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
//		session.setAttribute(ConstantsUtils.SESSION_USER, entity);*/
//		String username= account.getName();
//		//Map<String, String> mappwd=EncryptDecode.encryptPassword(username, account.getPassword());
//		char[] password=account.getPassword().toCharArray();//DigestUtils.md5Hex(account.getPassword()).toCharArray();
//		String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
//		String captcha=verifycode;
//		String stype=account.getStype();
//		rememberMe =rememberMe==null?false:rememberMe;
//		
//		UsernamePasswordCaptchaToken token=new UsernamePasswordCaptchaToken(username, password, rememberMe, host, captcha,stype, longUsernameType, mobileLogin);
//		Subject subject = SecurityUtils.getSubject();
//		
//		try {
//			subject.login(token);
//        } catch (UnknownAccountException e) {
//            logger.error("账号不存在：{}", e);
//            return MapResult.mapError(RspCode.R10000,"对不起，您账号不存在");
//        } catch (LockedAccountException e) {
//            logger.error("账号未启用：{}", e);
//            return MapResult.mapError(RspCode.R10006,"对不起，您账号被冻结");
//        } catch (IncorrectCredentialsException e) {
//            logger.error("密码错误：{}", e);
//            return MapResult.mapError(RspCode.R10001,"对不起，您账号或密码错误");
//        } catch (CaptchaException e) {
//        	logger.error("验证码错误："+ e.getMessage());
//        	return MapResult.mapError(RspCode.R10001,"对不起，验证码错误");
//        } catch (RuntimeException e) {
//            logger.error("未知错误,请联系管理员："+ e.getMessage());
//            return MapResult.mapError(RspCode.R10001,"对不起，未知错误,请联系管理员");
//        } 
//		
//		return MapResult.mapOK(RspCode.R00000);
//	}
	
//	@RequestMapping("out")
//	@ResponseBody
//	public Map<String,Object> out(HttpServletRequest request) {
//		/*request.getSession().removeAttribute(ConstantsUtils.SESSION_USER);
//		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);*/
//		return MapResult.mapOK("成功退出");
//	}
}
