package com.xzjie.cms.dto;

import com.xzjie.cms.model.Role;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RoleRequest extends BasePageRequest {
    private Long id;

    @NotBlank
    private String roleCode;

    @NotBlank
    private String roleName;

    private Integer roleLevel;

    private String roleDesc;

    private List<Long> menus;

    public Role toRole() {
        Role role = new Role();
        BeanUtils.copyProperties(this, role);
        return role;
    }
}
