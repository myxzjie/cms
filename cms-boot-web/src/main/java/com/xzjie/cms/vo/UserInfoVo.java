package com.xzjie.cms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzjie.cms.model.Account;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserInfoVo {
    private String name;
    private String nickName;
    private String sex;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate birtn;
    private String phone;
    private String email;
    private String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
    private String introduction = "'I am a super administrator'";
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
