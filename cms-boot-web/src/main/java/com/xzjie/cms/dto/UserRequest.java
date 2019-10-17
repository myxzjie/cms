package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.Article;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class UserRequest {
    private String name;
    private String nickName;
    private String sex;
    private LocalDate birtn;

    public Account toAccount() {
        Account model = new Account();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}
