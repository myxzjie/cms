package com.xzjie.cms.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Vito
 * @since 2022/6/16 3:17 下午
 */
@Data
public class BinderUserDto {
    @NotBlank
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
}
