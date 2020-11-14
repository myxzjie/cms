package com.xzjie.cms.dto;

import com.xzjie.cms.model.Navigation;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class NavigationRequest {
    private Long id;
    private Long pid;
    private String name;
    private String url;
    private String target;
    private Integer sort;
    private Boolean enabled;

    public Navigation toNavigation() {
        Navigation navigation = new Navigation();
        BeanUtils.copyProperties(this, navigation);
        return navigation;
    }

    public @interface Update {
    }
}
