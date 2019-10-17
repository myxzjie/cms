package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class MenuResponse {

    private Long id;

    private Long pid;

    private Integer type;

    private String permission;

    private String name;

    private Integer sort;

    private String path;

    private String component;

    private Boolean cache;

    private Boolean hidden;

    private String componentName;

    private String icon;

    private List<MenuResponse> children = new ArrayList<>();

    private LocalDateTime createDate;
}
