package com.xzjie.cms.security.code;

import com.xzjie.cms.security.SecurityUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Vito
 * @since 2022/6/11 2:24 上午
 */
public class CodeAuthenticationProvider implements AuthenticationProvider {

    private SecurityUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CodeAuthenticationToken authenticationToken = (CodeAuthenticationToken) authentication;

        String mobile = (String) authenticationToken.getPrincipal();

//        checkCode(mobile);

        UserDetails userDetails = userDetailsService.loadUserByMobile(mobile);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        CodeAuthenticationToken authenticationResult = new CodeAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    private void checkCode(String mobile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCode = request.getParameter("code");

        Map<String, Object> smsCode = (Map<String, Object>) request.getSession().getAttribute("smscode");
        if(smsCode == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }

        String applyMobile = (String) smsCode.get("mobile");
        int code = (int) smsCode.get("code");

        if(!applyMobile.equals(mobile)) {
            throw new BadCredentialsException("申请的手机号码与登录手机号码不一致");
        }
        if(code != Integer.parseInt(inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }
    }



    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return CodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

//    public UserDetailsService getUserDetailsService() {
//        return userDetailsService;
//    }

    public void setUserDetailsService(SecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}