package com.xzjie.cms.system.role.web;

import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.dto.RoleRequest;
import com.xzjie.cms.model.Permission;
import com.xzjie.cms.system.role.model.Role;
import com.xzjie.cms.system.role.serivce.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/role")
public class RoleAuthorizationController {
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/query")
    @PreAuthorize("@permission.hasPermission('administrator','role:all','role:list')")
    public Result<List<Role>> roleList() {
        List<Role> roles = roleService.getRoles();
        return Result.data(roles);
    }

    @GetMapping(value = "/list")
    @PreAuthorize("@permission.hasPermission('administrator','role:all','role:list')")
    public Result<List<Role>> roleList(RoleRequest roleRequest) {
        Page<Role> rolePage = roleService.getRole(roleRequest.getPage(), roleRequest.getSize(), roleRequest.toRole());
        return Result.data(rolePage.getContent(), rolePage.getTotalElements());
    }

    @GetMapping(value = "/permission/{roleId}")
    @PreAuthorize("@permission.hasPermission('administrator','role:all','role:list')")
    public Result<List<Long>> getRolePermission(@PathVariable Long roleId) {
        List<Long> permissions = roleService.getRolePermission(roleId)
                .stream()
                .map(Permission::getMenuId)
                .collect(Collectors.toList());

        return Result.data(permissions);
    }


    @Log("创建角色权限")
    @ApiOperation("创建角色权限")
    @PostMapping("/create")
    @PreAuthorize("@permission.hasPermission('administrator','role:all','role:add')")
    public Result create(@Validated @RequestBody RoleRequest roleRequest) {
        roleService.save(roleRequest.toRole(), roleRequest.getMenus());
        return Result.success();
    }

    @Log("修改角色权限")
    @ApiOperation("修改角色权限")
    @PutMapping("/update")
    @PreAuthorize("@permission.hasPermission('administrator','role:all','role:edit')")
    public Result update(@Validated @RequestBody RoleRequest roleRequest) {
        roleService.update(roleRequest.toRole(), roleRequest.getMenus());
        return Result.success();
    }

    @DeleteMapping("/delete/{roleId}")
    @PreAuthorize("@permission.hasPermission('administrator','role:all','role:delete')")
    public Result delete(@PathVariable Long roleId) {
        roleService.delete(roleId);
        return Result.success();
    }
}
