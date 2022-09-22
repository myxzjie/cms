package com.xzjie.cms.system.auth.web;

import cn.hutool.core.lang.UUID;
import com.xkcoding.justauth.AuthRequestFactory;
import com.xzjie.cms.configure.CmsProperties;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.system.auth.model.Social;
import com.xzjie.cms.security.token.SecurityTokenProvider;
import com.xzjie.cms.system.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/oauth")
@Api(value = "管理端-第三方登录授权",tags = "管理端-第三方登录授权")
public class OauthController {
    @Autowired
    private AuthRequestFactory factory;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SecurityTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CmsProperties properties;

    @GetMapping("/list")
    public List<String> list() {
        return factory.oauthList();
    }

    @GetMapping("/{type}/login")
    @ApiOperation("第三方登录授权")
    public void login(@PathVariable String type, String redirect, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        String url = authRequest.authorize(AuthStateUtils.createState());
        response.sendRedirect(url);
    }

    @ApiOperation("第三方登录授权回调")
    @RequestMapping("/{type}/callback")
    public void login(@PathVariable String type, String redirect, AuthCallback callback, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        AuthResponse<AuthUser> authResponse = authRequest.login(callback);
        if (authResponse.ok()) {
            AuthUser user = authResponse.getData();
            Social social = accountService.getSocialByUuid(user.getUuid());
            if (social == null) {
                social = accountService.saveSocial(Social.builder()
                        .avatar(user.getAvatar())
                        .blog(user.getBlog())
                        .company(user.getCompany())
                        .email(user.getEmail())
                        .gender(user.getGender().getCode())
                        .location(user.getLocation())
                        .nickname(user.getNickname())
                        .remark(user.getRemark())
                        .username(user.getUsername())
                        .uuid(user.getUuid())
                        .source(user.getSource())
                        .build());
            }

            String code = UUID.fastUUID().toString();
            redisTemplate.opsForValue()
                    .set("social:" + code, social, 10 * 60, TimeUnit.SECONDS);
            if (social.getUserId() == null) {
                redirect += "#/binder?code=" + code;
            } else {
                redirect += "#/auth-redirect?code=" + code;
            }
        }
        //log.info("【response】= {}", JSON.toJSONString(authResponse));
        // redirect += "#/auth-redirect?code=1";
        response.sendRedirect(redirect);
    }

    @ApiOperation("第三方登录绑定")
    @GetMapping("/binder/{code}")
    public Map<String, Object> binder(@PathVariable String code, HttpServletResponse response) throws IOException {
        Social social = (Social) redisTemplate.opsForValue().get("bind:" + code);
        if (social == null) {
            return MapUtils.error("不存在或者已经过期[" + code + "]");
        }
        accountService.saveSocial(social);

        redisTemplate.delete("bind:" + code);

        response.sendRedirect(properties.getSystemUrl());
        return null;
    }

}
