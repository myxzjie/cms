package com.xzjie.cms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmailRequest {

    private String code;
    @NotBlank
    private String email;

    private String password;

}
