package com.xzjie.cms.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.xzjie.cms.core.utils.MapUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Map<String, Object> result = MapUtils.error(4);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(result));
    }
}
