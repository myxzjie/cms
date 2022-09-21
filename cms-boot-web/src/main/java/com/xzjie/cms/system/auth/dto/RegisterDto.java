package com.xzjie.cms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterRequest {
    @NotBlank(message = "code不能为空")
    private String code;
    @NotBlank(message = "用户名不能为空")
    private String name;
    private String phone;
    @NotBlank(message = "密码不能为空")
    private String password;
}
