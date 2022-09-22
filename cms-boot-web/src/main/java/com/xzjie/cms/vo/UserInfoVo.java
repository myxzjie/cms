package com.xzjie.cms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzjie.cms.system.account.model.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;

@Data
@ApiModel("用户对象")
public class UserInfoVo {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("生日")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate birtn;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("图像")
    private String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
    @ApiModelProperty("说明")
    private String introduction = "'I am a super administrator'";
    @ApiModelProperty("角色")
    private List<String> roles;

    private UserInfoVo toUserResponse(Account account) {
        BeanUtils.copyProperties(account, this);
        return this;
    }

    public static class UserResponseBuilder {
        private Account account;

        public static UserResponseBuilder builder() {
            return new UserResponseBuilder();
        }

        public UserResponseBuilder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public UserInfoVo build() {
            return new UserInfoVo().toUserResponse(account);
        }
    }
}
