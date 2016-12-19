package com.xzjie.gypt.system.web.controller;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.ExcessiveAttemptsException;
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
    public String login() {
        return "login";
    }
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public String showLoginForm(Account account,HttpServletRequest request, Model model) {
		String error = null;
        String exceptionClassName = (String)request.getAttribute(FormAuthenticationCaptchaFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        logger.error(">> exceptionClassName:"+exceptionClassName);
        if(AccountException.class.getName().equals(exceptionClassName)){
        	error = "对不起，您输入用户名和密码";
        }else if(UnknownAccountException.class.getName().equals(exceptionClassName)||IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "对不起，您输入用户名/密码错误";
        }  else if(CaptchaException.class.getName().equals(exceptionClassName)) {
        	error="对不起，您输入验证码错误";
        } else if(LockedAccountException.class.getName().equals(exceptionClassName)) {
        	error="对不起，您账号被冻结,请联系管理员";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
        	error="对不起，您重复登录错误超过5次,请等待 30分钟";
        }else if(exceptionClassName != null) {
            error = "対不起，业务繁忙." ;//+ (String)request.getAttribute("message");
        }
        
        model.addAttribute("error",  error);
        
        return "login";//redirect:
    }
	
//	
	
//	@RequestMapping("out")
//	@ResponseBody
//	public Map<String,Object> out(HttpServletRequest request) {
//		/*request.getSession().removeAttribute(ConstantsUtils.SESSION_USER);
//		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);*/
//		return MapResult.mapOK("成功退出");
//	}
}
