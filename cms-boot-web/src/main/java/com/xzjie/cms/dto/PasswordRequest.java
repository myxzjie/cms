package com.xzjie.cms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordRequest {
    @NotBlank(message = "原密码不能为空")
    @ApiModelProperty("原密码")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty("新密码")
    private String password;

}
