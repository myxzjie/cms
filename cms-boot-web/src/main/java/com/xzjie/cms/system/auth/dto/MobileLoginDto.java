package com.xzjie.cms.system.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MobileLoginDto {
    @NotBlank
    private String mobile;
    @NotBlank
    private String code;

}
