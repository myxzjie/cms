package com.xzjie.cms.system.account.dto;

import com.xzjie.cms.dto.Dto;
import com.xzjie.cms.model.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class AccountDto extends Dto {
    @ApiModelProperty("用户名")
    @NotBlank(groups = {Create.class, Update.class})
    private String name;
    @ApiModelProperty("密码")
    @NotBlank(groups = {Create.class})
    private String password;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户头像")
    @NotNull(groups = {Create.class, Update.class})
    private String avatar;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("生日")
    private LocalDate birtn;
    @ApiModelProperty("是否锁")
    @NotNull(groups = {Create.class, Update.class})
    private Integer locked;
    @ApiModelProperty("角色")
    private List<Long> roles;

    public Account toAccount() {
        Account model = new Account();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}
