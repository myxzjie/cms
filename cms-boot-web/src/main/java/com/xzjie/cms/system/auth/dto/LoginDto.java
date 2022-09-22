package com.xzjie.cms.system.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("登录对象")
public class LoginDto {
    @NotBlank(message = "{login.username}")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "{login.password}")
    @Length(min = 6,max = 18,message = "{login.password.length}")
    @ApiModelProperty("密码")
    private String password;

}
