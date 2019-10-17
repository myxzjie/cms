package com.xzjie.cms.system.web;

import cn.hutool.json.JSONUtil;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {
    @Autowired
    private AuthRequestFactory factory;

    @GetMapping("/{type}/login")
    public void login(@PathVariable String type, String redirect, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/{type}/callback")
    public void login(@PathVariable String type, String redirect, AuthCallback callback, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        AuthResponse authResponse = authRequest.login(callback);
        log.info("【response】= {}", JSONUtil.toJsonStr(authResponse));
        response.sendRedirect(redirect);
    }
}
