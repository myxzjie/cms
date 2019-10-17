package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuRouter {

    private Long id;

    private Long pid;

    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private MenuMeta meta;

    private List<MenuRouter> children = new ArrayList<>();
}
