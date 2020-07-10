package com.xzjie.cms.dto;

import com.xzjie.cms.model.Menu;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
public class MenuRequest {

    @NotNull(groups = {Update.class})
    private Long id;

    private Long pid;

    private Integer type;

    private String permission;

    @NotBlank
    private String name;

    private Integer sort;

    private String path;

    private String component;

    private Boolean cache;

    private Boolean hidden;

    private String componentName;

    private String icon;

    private LocalDateTime createDate;

    public Menu toMenu() {
        Menu menu = new Menu();
        BeanUtils.copyProperties(this, menu);
        return menu;
    }

    public @interface Update {}
}
