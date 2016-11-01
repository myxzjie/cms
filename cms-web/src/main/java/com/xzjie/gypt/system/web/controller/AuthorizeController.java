package com.xzjie.gypt.system.web.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mchange.v2.util.ResourceClosedException;
import com.xzjie.gypt.common.security.CaptchaException;
import com.xzjie.gypt.common.security.FormAuthenticationCaptchaFilter;
import com.xzjie.gypt.common.security.UsernamePasswordCaptchaToken;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.StringUtils;
import com.xzjie.gypt.system.security.SystemAuthorizingRealm.Principal;
import com.xzjie.gypt.system.service.OAuthService;
import com.xzjie.gypt.system.service.OrgService;

/**
 * 
 * @className AuthorizeController.java
 * @description TODO
 * @author xzjie
 * @create 2016年8月3日 上午12:06:20 
 * @version V0.0.1
 */
@Controller
public class AuthorizeController extends BaseController{

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private OAuthService oAuthService;
    @Autowired
    private OrgService orgService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/authorize")
    public Object authorize(Model model,HttpServletRequest request) throws URISyntaxException, OAuthSystemException {
    	//构建OAuth 授权请求
        
        try {
        	OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

            //检查传入的客户端id是否正确
            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
                OAuthResponse response =OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                //.setError(OAuthError.TokenResponse.INVALID_CLIENT)
                				.setError(RspCode.R30001)
                                //.setErrorDescription(Constants.INVALID_CLIENT_DESCRIPTION)
                                //.setErrorDescription(RspCode.getDesc(RspCode.R30001))
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }


            Subject subject = SecurityUtils.getSubject();
            //如果用户没有登录，跳转到登陆页面
            if(!subject.isAuthenticated()) {
                if(!login(subject, request)) {//登录失败时跳转到登陆页面
                	
                	Long orgId=Long.parseLong(oauthRequest.getClientId());
                	
                    model.addAttribute("client", orgService.get(orgId));
                    
                    return "oauth2login";
                }
            }

            //String username = (String)subject.getPrincipal();
            Principal principal=getPrincipal();
            //生成授权码
            String authorizationCode = null;
            //responseType目前仅支持CODE，另外还有TOKEN
            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(ResponseType.CODE.toString())) {
                OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
                authorizationCode = oauthIssuerImpl.authorizationCode();
                oAuthService.addAuthCode(authorizationCode, principal.getUsername());
            }

            //进行OAuth响应构建
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
            //设置授权码
            builder.setCode(authorizationCode);
            //得到到客户端重定向地址
            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

            //构建响应
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();

            //根据OAuthResponse返回ResponseEntity响应
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthProblemException e) {

            //出错处理
            String redirectUri = e.getRedirectUri();
            if (OAuthUtils.isEmpty(redirectUri)) {
                //告诉客户端没有传入redirectUri直接报错
                return new ResponseEntity("OAuth callback url needs to be provided by client!!!", HttpStatus.NOT_FOUND);
            }

            //返回错误消息（如?error=）
            final OAuthResponse response =
                    OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND)
                            .error(e).location(redirectUri).buildQueryMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        }
    }

    private boolean login(Subject subject, HttpServletRequest request) {
        if("get".equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String username=request.getParameter(FormAuthenticationCaptchaFilter.DEFAULT_USERNAME_PARAM);
        String password=request.getParameter(FormAuthenticationCaptchaFilter.DEFAULT_PASSWORD_PARAM);
        String stype=(String)request.getParameter(FormAuthenticationCaptchaFilter.DEFAULT_STYPE_PARAM);
        String captcha= (String) request.getParameter(FormAuthenticationCaptchaFilter.DEFAULT_CAPTCHA_PARAM);
        boolean rememberMe=Boolean.parseBoolean(request.getParameter(FormAuthenticationCaptchaFilter.DEFAULT_REMEMBER_ME_PARAM));;
        boolean isMobile=Boolean.parseBoolean(request.getParameter(FormAuthenticationCaptchaFilter.DEFAULT_IS_MOBILE_PARAM));;
        String host = StringUtils.getRemoteAddr((HttpServletRequest)request);

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
        	request.setAttribute("error", "对不起，您输入用户名和密码");
            return false;
        }
        
        char[] passwordChar=password.toCharArray();

        UsernamePasswordCaptchaToken token=new UsernamePasswordCaptchaToken(username, passwordChar, rememberMe, host, captcha, stype, isMobile);
        //UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            return true;
        } catch (Exception e) {
        	String error = null;
            String exceptionClassName = e.getClass().getName();
            
            if(AccountException.class.getName().equals(exceptionClassName)){
            	error = "对不起，您输入用户名和密码";
            }else if(UnknownAccountException.class.getName().equals(exceptionClassName)||IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "对不起，您输入用户名/密码错误";
            }  else if(CaptchaException.class.getName().equals(exceptionClassName)) {
            	error="对不起，您输入验证码错误";
            } else if(LockedAccountException.class.getName().equals(exceptionClassName)) {
            	error="对不起，您账号被冻结,请联系管理员";
            } else if(exceptionClassName != null) {
                error = "未知错误:," + (String)request.getAttribute("message");
            }
            
            request.setAttribute("error",  error);
            return false;
        }
    }
}