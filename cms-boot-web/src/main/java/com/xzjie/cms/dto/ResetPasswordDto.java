package com.xzjie.cms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ResetPasswordDto {
    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty("用户ID")
    private Long userId;
}
