package com.xzjie.cms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class MenuVo {

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

    private List<MenuVo> children = new ArrayList<>();

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
}
