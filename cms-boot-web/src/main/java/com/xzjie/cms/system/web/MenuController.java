package com.xzjie.cms.system.web;

import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.dto.MenuRequest;
import com.xzjie.cms.dto.MenuResponse;
import com.xzjie.cms.dto.MenuRouter;
import com.xzjie.cms.dto.MenuTree;
import com.xzjie.cms.model.Menu;
import com.xzjie.cms.security.SecurityUserDetails;
import com.xzjie.cms.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Log("新增菜单")
    @ApiOperation("新增菜单")
    @PostMapping("/create")
//    @PreAuthorize("@el.check('menu:add')")
    public Map<String, Object> create(@Validated @RequestBody MenuRequest menuRequest) {
        Menu menu = menuRequest.toMenu();
        menu.setState(1);
        menuService.save(menu);
        return MapUtils.success();
    }

    @Log("修改菜单")
    @ApiOperation("修改菜单")
    @PutMapping("/update")
//    @PreAuthorize("@el.check('menu:edit')")
    public Map<String, Object> update(@Validated(MenuRequest.Update.class) @RequestBody MenuRequest menuRequest) {
        menuService.update(menuRequest.toMenu());
        return MapUtils.success();
    }

    @Log("删除菜单")
    @ApiOperation("删除菜单")
    @DeleteMapping("/delete")
//    @PreAuthorize("@el.check('menu:del')")
    public Map<String, Object> delete(@Validated @RequestBody @NotNull Set<Long> ids) {
        menuService.delete(ids);
        return MapUtils.success();
    }

    @GetMapping("/routers")
    public Map<String, Object> getMenuRouter() {
        List<MenuRouter> menuRouters = menuService.getMenuRouter(SecurityUtils.getUserRoles());
        return MapUtils.success(menuRouters);
    }

    @Log("查询菜单")
    @ApiOperation("查询菜单")
    @GetMapping("/list")
    public Map<String, Object> getMenus() {
        List<MenuResponse> menus = menuService.getMenus();
        return MapUtils.success(menus);
    }

    @Log("查询菜单树")
    @ApiOperation("查询菜单树")
    @GetMapping("/tree")
    public Map<String, Object> tree() {
        List<MenuTree> menuTree = menuService.getMenuTree(0L);
        return MapUtils.success(menuTree);
    }
}
