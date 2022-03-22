package com.xzjie.cms.dto;

import com.xzjie.cms.model.Account;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import com.xzjie.cms.persistence.enums.ConditionType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto extends Dto {
    @NotBlank(groups = {Create.class, Update.class})
    private String name;
    @NotBlank(groups = {Create.class})
    private String password;
    private String phone;
    private String email;
    private String nickName;
    @NotNull(groups = {Create.class, Update.class})
    private String avatar;
    private String sex;
    private LocalDate birtn;
    @NotNull(groups = {Create.class, Update.class})
    private Integer locked;
    private List<Long> roles;

    public Account toAccount() {
        Account model = new Account();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}
