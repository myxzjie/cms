package com.xzjie.cms.system.menu.web;

import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.dto.MenuRequest;
import com.xzjie.cms.dto.MenuRouter;
import com.xzjie.cms.dto.MenuTree;
import com.xzjie.cms.system.menu.model.Menu;
import com.xzjie.cms.system.menu.service.MenuService;
import com.xzjie.cms.system.menu.vo.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/menu")
@Api(value = "管理端-菜单管理",tags = "管理端-菜单管理")
public class SystemMenuController {

    @Autowired
    private MenuService menuService;

    @Log("新增菜单")
    @ApiOperation("新增菜单")
    @PostMapping("/create")
    @PreAuthorize("@permission.hasPermission('administrator','menu:all','menu:add')")
    public Result create(@Validated @RequestBody MenuRequest menuRequest) {
        Menu menu = menuRequest.toMenu();
        menu.setState(1);
        menuService.save(menu);
        return Result.success();
    }

    @Log("修改菜单")
    @ApiOperation("修改菜单")
    @PutMapping("/update")
    @PreAuthorize("@permission.hasPermission('administrator','menu:all','menu:edit')")
    public Result update(@Validated(MenuRequest.Update.class) @RequestBody MenuRequest menuRequest) {
        menuService.update(menuRequest.toMenu());
        return Result.success();
    }

    @Log("删除菜单")
    @ApiOperation("删除菜单")
    @DeleteMapping("/delete")
    @PreAuthorize("@permission.hasPermission('administrator','menu:all','menu:delete')")
    public Result delete(@Validated @RequestBody @NotNull Set<Long> ids) {
        menuService.delete(ids);
        return Result.success();
    }

    @GetMapping("/routers")
    @ApiOperation("获得菜单路由数据")
    @PreAuthorize("@permission.hasPermission('administrator','menu:all','menu:list')")
    public Result getMenuRouter() {
        List<MenuRouter> menuRouters = menuService.getMenuRouter(SecurityUtils.getUserRoles());
        return Result.data(menuRouters);
    }

    @Log("查询菜单")
    @ApiOperation("查询菜单")
    @GetMapping("/list")
    @PreAuthorize("@permission.hasPermission('administrator','menu:all','menu:list')")
    public Result getMenus() {
        List<MenuVo> menus = menuService.getMenus();
        return Result.data(menus);
    }

    @Log("查询菜单树")
    @ApiOperation("查询菜单树")
    @GetMapping("/tree")
    @PreAuthorize("@permission.hasPermission('administrator','menu:all','menu:list')")
    public Result tree() {
        List<MenuTree> menuTree = menuService.getMenuTree(0L);
        return Result.data(menuTree);
    }
}
