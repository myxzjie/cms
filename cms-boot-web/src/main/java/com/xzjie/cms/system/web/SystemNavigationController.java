package com.xzjie.cms.system.web;

import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.navigation.dto.NavigationRequest;
import com.xzjie.cms.dto.NodeTree;
import com.xzjie.cms.navigation.model.Navigation;
import com.xzjie.cms.navigation.service.NavigationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/navigation")
public class SystemNavigationController {

    @Autowired
    private NavigationService navigationService;

    @GetMapping("/data")
    @PreAuthorize("@permission.hasPermission('administrator','navigation:all','navigation:list')")
    public Map<String, Object> getNavigations() {
        List<Navigation> navigations = navigationService.getNavigations(0L, true);
        return MapUtils.success(navigations);
    }

    @Log("查询导航栏")
    @ApiOperation("查询导航栏")
    @GetMapping("/list")
    @PreAuthorize("@permission.hasPermission('administrator','navigation:all','navigation:list')")
    public Map<String, Object> getNavigation() {
        List<Navigation> navigations = navigationService.getNavigation(0L);
        return MapUtils.success(navigations);
    }

    @GetMapping("/tree")
    @PreAuthorize("@permission.hasPermission('administrator','navigation:all','navigation:list')")
    public Map<String, Object> tree() {
        List<NodeTree> trees = navigationService.getNavigationTree(0L);
        return MapUtils.success(trees);
    }

    @PostMapping("/create")
    @PreAuthorize("@permission.hasPermission('administrator','navigation:all','navigation:add')")
    public Map<String, Object> create(@Validated @RequestBody NavigationRequest navigation) {
        navigationService.save(navigation.toNavigation());
        return MapUtils.success();
    }

    @PutMapping("/update")
    @PreAuthorize("@permission.hasPermission('administrator','navigation:all','navigation:edit')")
    public Map<String, Object> update(@Validated @RequestBody NavigationRequest navigation) {
        navigationService.update(navigation.toNavigation());
        return MapUtils.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("@permission.hasPermission('administrator','navigation:all','navigation:delete')")
    public Map<String, Object> delete(@Validated @RequestBody @NotNull Set<Long> ids) {
        navigationService.delete(ids);
        return MapUtils.success();
    }
}
