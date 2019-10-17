package com.xzjie.cms.dto;

import com.xzjie.cms.model.Account;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
