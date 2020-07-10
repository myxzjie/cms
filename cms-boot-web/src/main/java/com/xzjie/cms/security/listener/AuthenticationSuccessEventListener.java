package com.xzjie.cms.security.listener;

import com.xzjie.cms.security.LoginEventService;
import com.xzjie.cms.security.SecurityUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    private LoginEventService loginAttemptService;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        log.info("AuthenticationSuccessEventListener");
        SecurityUserDetails userDetails  =(SecurityUserDetails)authenticationSuccessEvent.getAuthentication().getPrincipal();
        log.info("userName:{} login success!",userDetails.getUsername());
        loginAttemptService.loginSucceeded(userDetails.getUsername());
    }
}
