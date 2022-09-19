package com.xzjie.cms.system.account.dto;

import com.xzjie.cms.dto.BasePageDto;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import com.xzjie.cms.persistence.enums.ConditionType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AccountQueryDto extends BasePageDto {
    //    @NotNull(groups = {Update.class})
//    private Long userId;
    @ApiModelProperty("用户名")
    @QueryCondition(connect = ConditionType.LIKE)
    @NotBlank(groups = {Update.class})
    private String name;
    @ApiModelProperty("手机号")
    @QueryCondition
    private String phone;
    @ApiModelProperty("邮箱")
    @QueryCondition
    private String email;
    @ApiModelProperty("用户昵称")
    private String nickName;
//
//    private String avatar;
//    private String sex;
//    private LocalDate birtn;
    @NotNull(groups = {Update.class})
    @ApiModelProperty("是否锁")
    private Integer locked;
//    private List<Long> roles;

}
