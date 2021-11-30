package com.xzjie.cms.system.web;

import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.dto.EmailRequest;
import com.xzjie.cms.dto.PasswordRequest;
import com.xzjie.cms.dto.UserRequest;
import com.xzjie.cms.dto.UserResponse;
import com.xzjie.cms.enums.VerifyCodeScenes;
import com.xzjie.cms.enums.VerifyCodeType;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.SystemLog;
import com.xzjie.cms.security.SecurityUserDetails;
import com.xzjie.cms.service.AccountService;
import com.xzjie.cms.service.SystemLogService;
import com.xzjie.cms.service.VerifyCodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class SystemUserController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SystemLogService systemLogService;
    @Autowired
    private VerifyCodeService verifyCodeService;

    @Log(value = "user_info", descrption = "获得用户信息")
    @GetMapping("/info")
    @PreAuthorize("hasAuthority('user')")
    public Map<String, Object> getUserDetails(Principal principal) {
        Map<String, Object> map = new HashMap<>();
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        Account account = accountService.getAccount(userDetails.getUserId());
        UserResponse user = UserResponse.UserResponseBuilder.builder().setAccount(account).build();
        user.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        map.put("code", 0);
        map.put("data", user);
        return map;
    }

    @ApiOperation("退出登录")
    @DeleteMapping(value = "/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
//        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        map.put("code", 0);
        return map;
    }

    @GetMapping("/record")
    @PreAuthorize("hasAuthority('user')")
    public Map<String, Object> record() {
        Map<String, Object> map = new HashMap<>();

        List<SystemLog> records = systemLogService.getLoginSystemLog(SecurityUtils.getUsername());

        map.put("code", 0);
        map.put("data", records);
        return map;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@Valid @RequestBody UserRequest user) {
        Map<String, Object> map = new HashMap<>();
        Account account = user.toAccount();
        account.setUserId(SecurityUtils.getUserId());
        account.setCreateUser(SecurityUtils.getUserId());
        accountService.update(account);
        map.put("code", 0);
        return map;
    }

    @PutMapping("/update/avatar")
    public Map<String, Object> update(@Valid @RequestBody @NotBlank String avatar) {
        Map<String, Object> map = new HashMap<>();
        accountService.updateAvatar(SecurityUtils.getUsername(), avatar);
        map.put("code", 0);
        return map;
    }

    @PutMapping("/update/password")
    public Map<String, Object> update(@Valid @RequestBody PasswordRequest password) throws Exception {
        Map<String, Object> map = new HashMap<>();
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        if (!passwordEncoder.matches(password.getOldPassword(), userDetails.getPassword())) {
            throw new Exception("修改失败，旧密码错误");
        }
        if (passwordEncoder.matches(password.getPassword(), userDetails.getPassword())) {
            throw new Exception("新密码不能与旧密码相同");
        }
        accountService.updatePassword(userDetails.getUserId(), passwordEncoder.encode(password.getPassword()));
        map.put("code", 0);
        return map;
    }

    @PutMapping("/update/email")
    public Map<String, Object> updateEmail(@Valid @RequestBody EmailRequest emailRequest) throws Exception {
        Map<String, Object> map = new HashMap<>();
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        if (!passwordEncoder.matches(emailRequest.getPassword(), userDetails.getPassword())) {
            throw new Exception("密码错误");
        }

        boolean verify = verifyCodeService.validated(emailRequest.getCode(), emailRequest.getEmail(), VerifyCodeScenes.EMAIL_CHANGE, VerifyCodeType.EMAIL);

        if (!verify) {
            throw new Exception("验证密码错误");
        }
        accountService.updateEmail(userDetails.getUserId(), emailRequest.getEmail());
        map.put("code", 0);
        return map;
    }

    @PostMapping("/verify/email")
    public Map<String, Object> resetEmail(@Valid @RequestBody @NotBlank String email) {
        Map<String, Object> map = new HashMap<>();
        verifyCodeService.sendMail(email);
        map.put("code", 0);
        return map;
    }

    @GetMapping("/list")
    public Map<String, Object> getUserList(UserRequest request) {
        Page<Account> page = accountService.getAccountList(request);
        return MapUtils.success(page.getContent(), page.getTotalElements());
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Valid @RequestBody UserRequest request) {
        accountService.save(request.toAccount());
        return MapUtils.success();
    }

}
