package com.xzjie.cms.system.web;

import cn.hutool.core.lang.UUID;
import com.xzjie.cms.configure.CmsProperties;
import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.event.EmailEvent;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.*;
import com.xzjie.cms.enums.VerifyCodeScenes;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.Social;
import com.xzjie.cms.security.code.CodeAuthenticationToken;
import com.xzjie.cms.security.social.SocialAuthenticationToken;
import com.xzjie.cms.security.token.SecurityTokenProvider;
import com.xzjie.cms.system.account.service.AccountService;
import com.xzjie.cms.system.account.dto.AccountDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Configuration configuration;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityTokenProvider tokenProvider;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CmsProperties properties;

    @PostMapping("/mobile")
    public Map<String, Object> mobile(@Valid @RequestBody Login2Request login) throws Exception {
        CodeAuthenticationToken authenticationToken = new CodeAuthenticationToken(login.getMobile());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken((UserDetails) authentication.getPrincipal());

        return MapUtils.success(new AuthResponse(token));
    }

    @Log(value = "login", descrption = "用户登录")
    @PostMapping("/sign")
    public Map<String, Object> authenticate(@Valid @RequestBody LoginRequest login) throws Exception {
        Authentication authentication = authenticate(login.getUsername(), login.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken((UserDetails) authentication.getPrincipal());

        return MapUtils.success(new AuthResponse(token));
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new Exception("用户名P已经被锁，请联系管理", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("用户名或密码错误", e);
        }
    }


    @PostMapping("/signup/binder")
    public Map<String, Object> registerUser(@Valid @RequestBody RegisterRequest register) {
        if (accountService.existsByName(register.getName())) {
            throw new RuntimeException("Username is already taken!");
        }
        Social social = (Social) redisTemplate.opsForValue().get("social:" + register.getCode());
        AccountDto account = new AccountDto();
        account.setName(register.getName());
        account.setPhone(register.getPhone());
        account.setPassword(passwordEncoder.encode(register.getPassword()));
        account.setRoles(Arrays.asList(4L));
        accountService.save(account, social, register.getCode());
        return MapUtils.success();
    }

    @PostMapping("/authorize")
    public Map<String, Object> authorize(String code) {
        Social social = (Social) redisTemplate.opsForValue().get("social:" + code);
        if (social == null) {
            return MapUtils.error("不存在或者已经过期[" + code + "]");
        }
        SocialAuthenticationToken authenticationToken = new SocialAuthenticationToken(social.getUserId());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken((UserDetails) authentication.getPrincipal());

        redisTemplate.delete("social:" + code);
        return MapUtils.success(new com.xzjie.cms.dto.AuthResponse(token));
    }

    @PostMapping("/validator/name")
    public Map<String, Object> validator(@RequestBody ValidatorDto params) {
        if (accountService.existsByName(params.getName())) {
            return MapUtils.success(true);
        }
        return MapUtils.success(false);
    }

    @PostMapping("/binder/email/validator")
    public Map<String, Object> binderUser(@Validated @RequestBody BinderUserDto params) {
        Social social = (Social) redisTemplate.opsForValue().get("social:" + params.getCode());
        if (social == null) {
            return MapUtils.error("不存在或者已经过期[" + params.getCode() + "]");
        }
        if (!accountService.existsByName(params.getName())) {
            return MapUtils.error("用户不存在");
        }

        Account account = accountService.getAccountByName(params.getName());
        if (!params.getEmail().equals(account.getEmail())) {
            return MapUtils.error("您输入的邮箱不匹配");
        }

        String code = UUID.fastUUID().toString();
        social.setUserId(account.getId());
        redisTemplate.opsForValue().set("bind:" + code, social, 2 * 60 * 60, TimeUnit.SECONDS);

        String url = properties.getUrl() + "/oauth/binder/" + code;

        String subject = VerifyCodeScenes.BINDER.getName();
        String content = null;
        try {
            Template template = configuration.getTemplate("email/binder_validator.html");
            StringWriter result = new StringWriter();
            template.process(MapUtils.create()
                    .set("source", social.getSource())
                    .set("url", url), result);
            content = result.toString(); // FreeMarkerTemplateUtils.processTemplateIntoString(template,)
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        applicationContext.publishEvent(EmailEvent.EmailEventBuilder
                .builder()
                .setSubject(subject)
                .setContent(content)
                .setTo(params.getEmail())
                .build());
        return MapUtils.success();
    }
}
