package com.xzjie.client.system.web.controller;

import com.xzjie.et.core.security.CaptchaException;
import com.xzjie.et.core.security.FormAuthenticationCaptchaFilter;
import com.xzjie.client.core.web.BaseController;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {

    @RequestMapping("login")
    public String login( Model model){
        return getRemoteView("login");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String showLoginForm(HttpServletRequest request, Model model) {
        String error = null;
        String exceptionClassName = (String)request.getAttribute(FormAuthenticationCaptchaFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        if(AccountException.class.getName().equals(exceptionClassName)){
            error = "对不起，您输入用户名";
        }  else if(UnknownAccountException.class.getName().equals(exceptionClassName)){
            error = "对不起，您输入用户名不存在";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
            error = "对不起，您输入用户名/密码错误";
        }  else if(CaptchaException.class.getName().equals(exceptionClassName)) {
            error="对不起，您输入验证码错误";
        } else if(LockedAccountException.class.getName().equals(exceptionClassName)) {
            error="对不起，您账号被冻结,请联系管理员";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
            error="重复密码错误超过5次,请等待30分钟...";
        }else if(exceptionClassName != null) {
            error = "登录系统错误";
        }

        model.addAttribute("error",  error);

        return getRemoteView("login");
//        return "redirect:/"+getTemplate()+"/login";
    }
}
