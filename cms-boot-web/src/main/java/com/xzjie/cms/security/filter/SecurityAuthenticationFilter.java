//package com.xzjie.cms.security.filter;
//
//import com.xzjie.cms.security.token.SecurityTokenProvider;
//import com.xzjie.cms.security.SecurityUserDetails;
//import com.xzjie.cms.security.SecurityUserDetailsService;
//import io.jsonwebtoken.ExpiredJwtException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class SecurityAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private SecurityTokenProvider tokenProvider;
//
//    @Autowired
//    private SecurityUserDetailsService userDetailsService;
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            String token = getTokenFromRequest(request);
//
//            if (StringUtils.isEmpty(token)) {
//                logger.warn("JWT Token does not begin with Bearer String");
//                filterChain.doFilter(request, response);
//                return;
//            }
//            String username = tokenProvider.getUsernameFromToken(token);
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                /*
//                    Note that you could also encode the user's username and roles inside JWT claims
//                    and create the UserDetails object by parsing those claims from the JWT.
//                    That would avoid the following database hit. It's completely up to you.
//                 */
//                SecurityUserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                // if token is valid configure Spring Security to manually set
//                // authentication
//                if (tokenProvider.validateToken(token, userDetails)) {
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    // After setting the Authentication in the context, we specify
//                    // that the current user is authenticated. So it passes the
//                    // Spring Security Configurations successfully.
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        } catch (IllegalArgumentException e) {
//            logger.error("Unable to get JWT Token");
//        } catch (ExpiredJwtException e) {
//            logger.error("JWT Token has expired");
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("Could not set user authentication in security context", e);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }
//
//}
