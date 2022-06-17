package com.xzjie.cms.security.code;

import com.xzjie.cms.security.SecurityUserDetailsService;
import com.xzjie.cms.security.authentication.SecurityAuthenticationFailureHandler;
import com.xzjie.cms.security.authentication.SecurityAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author Vito
 * @since 2022/6/11 9:17 上午
 */
@Component
@RequiredArgsConstructor
public class CodeSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final SecurityAuthenticationSuccessHandler authenticationSuccessHandler;
    private final SecurityAuthenticationFailureHandler authenticationFailureHandler;
    private final SecurityUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        CodeAuthenticationFilter authenticationFilter = new CodeAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        CodeAuthenticationProvider codeAuthenticationProvider = new CodeAuthenticationProvider();
        codeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(codeAuthenticationProvider)
                .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
