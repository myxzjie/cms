package com.xzjie.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class MenuMeta {

    private String title;

    private String icon;

    private Boolean noCache;

    private List<String> roles;
}
