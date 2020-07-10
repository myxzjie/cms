package com.xzjie.cms.security.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Value("${origin.access-control-allow-origin}")
    private String origin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", origin);
        res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//        res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");//服务器支持的所有头信息字段
//        response.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Credentials", "true");//是否可传递信息
//        res.setHeader("XDomainRequestAllowed","1");
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, res);
        }
    }

    @Override
    public void destroy() {

    }
}
