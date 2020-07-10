package com.xzjie.cms.security.listener;

import com.xzjie.cms.security.LoginEventService;
import com.xzjie.cms.security.SecurityUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoginEventService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        log.info("AuthenticationFailureListener");
        String username = (String) authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal();

        loginAttemptService.loginFailed(username);
    }
}
