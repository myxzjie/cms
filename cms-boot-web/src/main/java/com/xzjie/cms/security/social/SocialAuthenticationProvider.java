package com.xzjie.cms.security.social;

import com.xzjie.cms.security.SecurityUserDetailsService;
import com.xzjie.cms.security.code.CodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Vito
 * @since 2022/6/11 2:24 上午
 */
public class SocialAuthenticationProvider implements AuthenticationProvider {

    private SecurityUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialAuthenticationToken authenticationToken = (SocialAuthenticationToken) authentication;

        Long userId = (Long) authenticationToken.getPrincipal();

        UserDetails userDetails = userDetailsService.loadUserById(userId);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        SocialAuthenticationToken authenticationResult = new SocialAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return SocialAuthenticationToken.class.isAssignableFrom(authentication);
    }

//    public UserDetailsService getUserDetailsService() {
//        return userDetailsService;
//    }

    public void setUserDetailsService(SecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}