package com.xzjie.cms.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.dto.AuthResponse;
import com.xzjie.cms.security.token.SecurityTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private SecurityTokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        String token = tokenProvider.generateToken(SecurityUtils.getUserDetails());
        Map<String, Object> result = MapUtils.success(new AuthResponse(token)).set("message", "登录成功！");
        out.write(JSONObject.toJSONString(result));
        out.flush();
        out.close();
    }
}
