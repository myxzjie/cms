package com.xzjie.cms.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.xzjie.cms.core.utils.I18Utils;
import com.xzjie.cms.core.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
//        log.error("Responding with unauthorized error. Message - {}", e.getMessage());
//        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        httpServletResponse.setContentType("application/json;charset=utf-8");
        Map<String, Object> result = MapUtils.error(6); //, "需要权限,请先登录！"
        httpServletResponse.getWriter().write(JSONObject.toJSONString(result));
    }
}
