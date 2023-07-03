package com.xzjie.cms.security;

//import com.xzjie.cms.security.filter.SecurityAuthenticationFilter;
import com.xzjie.cms.security.authentication.*;
import com.xzjie.cms.security.code.CodeAuthenticationProvider;
import com.xzjie.cms.security.code.CodeSecurityConfigurerAdapter;
import com.xzjie.cms.security.permission.CustomPermissionEvaluator;
import com.xzjie.cms.security.social.SocialSecurityConfigurerAdapter;
import com.xzjie.cms.security.token.SecurityTokenProvider;
import com.xzjie.cms.security.token.TokenConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private CorsFilter corsFilter;
    @Autowired
    private SecurityTokenProvider tokenProvider;
    @Autowired
    private SecurityUserDetailsService userDetailsService;
    @Autowired
    private SecurityAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private SecurityAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SecurityAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private SecurityAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private SecurityLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CodeSecurityConfigurerAdapter codeSecurityConfigurerAdapter;
    @Autowired
    private SocialSecurityConfigurerAdapter securityConfigurerAdapter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF
                .csrf().disable()
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                // 授权异常
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)

                // 防止iframe 造成跨域
                .and()
                .headers()
                .frameOptions()
                .disable()

                // 不创建会话
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                // 静态资源等等
                .antMatchers("/css/*", "/js/**", "/image/**", "/fonts/**", "/i/**", "/img/**", "/logo.png", "favicon.ico", "/demo", "/api/hot/data").permitAll()
                .antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .antMatchers("/", "/index", "/article/**", "/login**", "/oauth/**", "/error**").permitAll()
                .antMatchers("/swagger-ui.html", "/doc.html", "/swagger-ui/*",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/webjars/**").permitAll()
                .antMatchers("/app/**", "/api/auth/**","/api/oauth/**","/api/wechat/**").permitAll()
                .antMatchers("/images/**").permitAll()
                // 所有请求都需要认证
                .anyRequest().authenticated()
                .and().apply(securityConfigurerAdapter())
                .and().apply(codeSecurityConfigurerAdapter)
                .and().apply(securityConfigurerAdapter);
//        http.authorizeRequests()
//
////                .antMatchers("/api/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
////                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .headers().frameOptions().disable()
////                .and()
////                .formLogin()
////                .loginProcessingUrl("/api/auth/sign") //.usernameParameter("username").passwordParameter("password")
////                .successHandler(authenticationSuccessHandler)
////                .failureHandler(authenticationFailureHandler)
////                .permitAll()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler)
////                .and().logout()
////                .logoutSuccessHandler(logoutSuccessHandler)
//                .and().addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//        ;
//
////        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

//    // 注入自定义url和权限验证器
//    @Bean
//    public DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler() {
//        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
//        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
//        return handler;
//    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityAuthenticationFilter authenticationFilter() {
//        return new SecurityAuthenticationFilter();
//    }

    private TokenConfigurer securityConfigurerAdapter() {
        return new TokenConfigurer(tokenProvider, userDetailsService);
    }
}
