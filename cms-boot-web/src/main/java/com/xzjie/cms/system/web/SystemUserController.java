package com.xzjie.cms.system.web;

import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.dto.*;
import com.xzjie.cms.enums.VerifyCodeScenes;
import com.xzjie.cms.enums.VerifyCodeType;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.SystemLog;
import com.xzjie.cms.security.SecurityUserDetails;
import com.xzjie.cms.service.AccountService;
import com.xzjie.cms.service.SystemLogService;
import com.xzjie.cms.service.VerifyCodeService;
import com.xzjie.cms.vo.UserInfoVo;
import com.xzjie.cms.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
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
//    @PreAuthorize("hasAuthority('user') or hasAuthority('member')")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all','user:list')")
    public Map<String, Object> getUserDetails(Principal principal) {
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        Account account = accountService.getAccount(userDetails.getUserId());
        UserInfoVo user = UserInfoVo.UserResponseBuilder.builder().setAccount(account).build();
        user.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return MapUtils.success(user);
    }

    @ApiOperation("退出登录")
    @DeleteMapping(value = "/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
//        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return MapUtils.success();
    }

    @GetMapping("/record")
//    @PreAuthorize("hasAuthority('user') or hasAuthority('member')")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Map<String, Object> record() {
        List<SystemLog> records = systemLogService.getLoginSystemLog(SecurityUtils.getUsername());
        return MapUtils.success(records);
    }

    @PutMapping("/update")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Map<String, Object> update(@Valid @RequestBody UserDto user) {
        Account account = user.toAccount();
        account.setUserId(SecurityUtils.getUserId());
        account.setCreateUser(SecurityUtils.getUserId());
        accountService.update(account);
        return MapUtils.success();
    }

    @PutMapping("/update/avatar")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Map<String, Object> update(@Valid @RequestBody @NotBlank String avatar) {
        accountService.updateAvatar(SecurityUtils.getUsername(), avatar);
        return MapUtils.success();
    }

    @PutMapping("/update/password")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Map<String, Object> update(@Valid @RequestBody PasswordRequest password) throws Exception {
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        if (!passwordEncoder.matches(password.getOldPassword(), userDetails.getPassword())) {
            throw new Exception("修改失败，旧密码错误");
        }
        if (passwordEncoder.matches(password.getPassword(), userDetails.getPassword())) {
            throw new Exception("新密码不能与旧密码相同");
        }
        accountService.updatePassword(userDetails.getUserId(), passwordEncoder.encode(password.getPassword()));
        return MapUtils.success();
    }

    @PutMapping("/update/email")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Map<String, Object> updateEmail(@Valid @RequestBody EmailRequest emailRequest) throws Exception {
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        if (!passwordEncoder.matches(emailRequest.getPassword(), userDetails.getPassword())) {
            throw new Exception("密码错误");
        }

        boolean verify = verifyCodeService.validated(emailRequest.getCode(), emailRequest.getEmail(), VerifyCodeScenes.EMAIL_CHANGE, VerifyCodeType.EMAIL);

        if (!verify) {
            throw new Exception("验证密码错误");
        }
        accountService.updateEmail(userDetails.getUserId(), emailRequest.getEmail());
        return MapUtils.success();
    }

    @PostMapping("/verify/email")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Map<String, Object> resetEmail(@Valid @RequestBody @NotBlank String email) {
        verifyCodeService.sendMail(email);
        return MapUtils.success();
    }

    @GetMapping("/list")
    @PreAuthorize("@permission.hasPermission('administrator','user:all','user:list')")
    public Map<String, Object> getUserList(UserQueryDto request) {
        Page<UserVo> page = accountService.getAccountList(request);
        return MapUtils.success(page.getContent(), page.getTotalElements());
    }

    @PostMapping("/create")
    @PreAuthorize("@permission.hasPermission('administrator','user:all')")
    public Map<String, Object> create(@Validated(UserDto.Create.class) @RequestBody UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        accountService.save(dto, null, null);
        return MapUtils.success();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','user:all')")
    public Map<String, Object> update(@PathVariable Long id, @Validated(UserDto.Update.class) @RequestBody UserDto dto) {
        accountService.update(id, dto);
        return MapUtils.success();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','user:all')")
    public Map<String, Object> delete(@PathVariable Long id) {
        accountService.delete(id);
        return MapUtils.success();
    }

}
