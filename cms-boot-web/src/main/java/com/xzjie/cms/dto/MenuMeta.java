package com.xzjie.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MenuMeta {

    private String title;

    private String icon;

    private Boolean noCache;
}
