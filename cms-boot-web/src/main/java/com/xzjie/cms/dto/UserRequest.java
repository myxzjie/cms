package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import com.xzjie.cms.persistence.enums.ConditionType;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class UserRequest extends BasePageRequest {
    @QueryCondition(connect = ConditionType.LIKE)
    private String name;
    @QueryCondition
    private String phone;
    @QueryCondition
    private String email;
    private String nickName;
    private String sex;
    private LocalDate birtn;

    public Account toAccount() {
        Account model = new Account();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}
