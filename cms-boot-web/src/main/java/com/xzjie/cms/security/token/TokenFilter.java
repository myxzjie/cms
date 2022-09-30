package com.xzjie.cms.security.token;

import com.alibaba.fastjson.JSONObject;
import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.enums.Business;
import com.xzjie.cms.security.SecurityUserDetails;
import com.xzjie.cms.security.SecurityUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class TokenFilter extends GenericFilterBean {

    private final SecurityTokenProvider tokenProvider;
    private final SecurityUserDetailsService userDetailsService;

    public TokenFilter(SecurityTokenProvider tokenProvider, SecurityUserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = resolveToken(request);
        String requestRri = request.getRequestURI();
        try {


            if (StringUtils.hasText(token) && !tokenProvider.isTokenExpired(token)) {

                String username = tokenProvider.getUsernameFromToken(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                /*
                    Note that you could also encode the user's username and roles inside JWT claims
                    and create the UserDetails object by parsing those claims from the JWT.
                    That would avoid the following database hit. It's completely up to you.
                 */
                    SecurityUserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // if token is valid configure Spring Security to manually set
                    // authentication
                    if (tokenProvider.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        // After setting the Authentication in the context, we specify
                        // that the current user is authenticated. So it passes the
                        // Spring Security Configurations successfully.
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestRri);
                    }
                }
            }
        } catch (ExpiredJwtException e) {
            servletResponse.setContentType("application/json;charset=utf-8");
            //"需要权限,请先登录！"
            servletResponse.getWriter().write(JSONObject.toJSONString(Result.fail(Business.TOKEN_EXPIRED.getCode(),"登录过期,请重新登录")));
            return;
        }

//        if (StringUtils.hasText(token) && tokenProvider.isTokenExpired(token)) {
//            Authentication authentication = tokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestRri);
//        } else {
//            log.debug("no valid JWT token found, uri: {}", requestRri);
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
