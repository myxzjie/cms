package com.xzjie.cms.system.account.web;

import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.dto.*;
import com.xzjie.cms.enums.VerifyCodeScenes;
import com.xzjie.cms.enums.VerifyCodeType;
import com.xzjie.cms.system.account.model.Account;
import com.xzjie.cms.model.SystemLog;
import com.xzjie.cms.security.SecurityUserDetails;
import com.xzjie.cms.system.account.service.AccountService;
import com.xzjie.cms.service.SystemLogService;
import com.xzjie.cms.service.VerifyCodeService;
import com.xzjie.cms.system.account.dto.AccountDto;
import com.xzjie.cms.system.account.dto.AccountQueryDto;
import com.xzjie.cms.vo.AccountVo;
import com.xzjie.cms.vo.UserInfoVo;
import io.swagger.annotations.Api;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@Api(value = "管理端-用户管理", tags = "管理端-用户管理")
public class SystemAccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SystemLogService systemLogService;
    @Autowired
    private VerifyCodeService verifyCodeService;

    @ApiOperation("获得登录用户信息")
    @Log(value = "user_info", descrption = "获得用户信息")
    @GetMapping("/info")
//    @PreAuthorize("hasAuthority('user') or hasAuthority('member')")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all','user:list')")
    public Result getUserDetails(Principal principal) {
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        Account account = accountService.getAccount(userDetails.getUserId());
        UserInfoVo user = UserInfoVo.UserResponseBuilder.builder().setAccount(account).build();
        user.setRoles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return Result.data(user);
    }

    @ApiOperation("退出登录")
    @DeleteMapping(value = "/logout")
    public Result logout(HttpServletRequest request) {
//        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return Result.success();
    }


    @ApiOperation("用户登录记录")
    @GetMapping("/record")
//    @PreAuthorize("hasAuthority('user') or hasAuthority('member')")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Result record() {
        List<SystemLog> records = systemLogService.getLoginSystemLog(SecurityUtils.getUsername());
        return Result.data(records);
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/update")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Result update(@Valid @RequestBody AccountDto user) {
        Account account = user.toAccount();
        account.setId(SecurityUtils.getUserId());
        account.setCreateUser(SecurityUtils.getUserId());
        accountService.update(account);
        return Result.success();
    }

    @ApiOperation("修改头像信息")
    @PutMapping("/update/avatar")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Result update(@Valid @RequestBody @NotBlank String avatar) {
        accountService.updateAvatar(SecurityUtils.getUsername(), avatar);
        return Result.success();
    }

    @ApiOperation("修改密码")
    @PutMapping("/update/password")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Result update(@Valid @RequestBody PasswordRequest password) throws Exception {
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        if (!passwordEncoder.matches(password.getOldPassword(), userDetails.getPassword())) {
            throw new Exception("修改失败，旧密码错误");
        }
        if (passwordEncoder.matches(password.getPassword(), userDetails.getPassword())) {
            throw new Exception("新密码不能与旧密码相同");
        }
        accountService.updatePassword(userDetails.getUserId(), passwordEncoder.encode(password.getPassword()));
        return Result.success();
    }

    @ApiOperation("重置密码")
    @PutMapping("/reset/password")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Result resetPassword(@Valid @RequestBody ResetPasswordDto params) {
        accountService.resetPassword(params.getUserId());
        return Result.success();
    }

    @ApiOperation("更换邮箱")
    @PutMapping("/update/email")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Result updateEmail(@Valid @RequestBody EmailRequest emailRequest) {
        SecurityUserDetails userDetails = SecurityUtils.getUserDetails();
        if (!passwordEncoder.matches(emailRequest.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        boolean verify = verifyCodeService.validated(emailRequest.getCode(), emailRequest.getEmail(), VerifyCodeScenes.EMAIL_CHANGE, VerifyCodeType.EMAIL);

        if (!verify) {
            throw new RuntimeException("验证密码错误");
        }
        accountService.updateEmail(userDetails.getUserId(), emailRequest.getEmail());
        return Result.success();
    }

    @ApiOperation("发送邮箱验证码")
    @PostMapping("/verify/email")
    @PreAuthorize("@permission.hasPermission('administrator','member','user:all')")
    public Map<String, Object> resetEmail(@Valid @RequestBody @NotBlank String email) {
        verifyCodeService.sendMail(email);
        return MapUtils.success();
    }

    @ApiOperation("获得用户列表")
    @GetMapping("/list")
    @PreAuthorize("@permission.hasPermission('administrator','user:all','user:list')")
    public Result getUserList(AccountQueryDto request) {
        Page<AccountVo> page = accountService.getAccountList(request);
        return Result.data(page.getContent(), page.getTotalElements());
    }

    @ApiOperation("创建用户")
    @PostMapping("/create")
    @PreAuthorize("@permission.hasPermission('administrator','user:all')")
    public Result create(@Validated(AccountDto.Create.class) @RequestBody AccountDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        accountService.save(dto, null, null);
        return Result.success();
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/update/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','user:all')")
    public Result update(@PathVariable Long id, @Validated(AccountDto.Update.class) @RequestBody AccountDto dto) {
        accountService.update(id, dto);
        return Result.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@permission.hasPermission('administrator','user:all','user:delete')")
    public Result delete(@PathVariable Long id) {
        accountService.delete(id);
        return Result.success();
    }

}
