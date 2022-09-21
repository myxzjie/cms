package com.xzjie.cms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Login2Request {
    @NotBlank
    private String mobile;
    @NotBlank
    private String code;

}
