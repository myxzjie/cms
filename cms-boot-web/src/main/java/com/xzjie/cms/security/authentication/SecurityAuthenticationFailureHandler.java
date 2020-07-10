package com.xzjie.cms.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.xzjie.cms.core.utils.MapUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        Map<String, Object> result;
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            result = MapUtils.error(1);
        } else if (e instanceof DisabledException) {
            result = MapUtils.error(2);
        } else {
            result = MapUtils.error(3, new String[]{"" + e.fillInStackTrace()});
        }
        out.write(JSONObject.toJSONString(result));
        out.flush();
        out.close();
    }
}
