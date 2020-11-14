package com.xzjie.cms.system.web;

import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.*;
import com.xzjie.cms.model.Navigation;
import com.xzjie.cms.service.NavigationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, Object> getNavigations() {
        List<Navigation> navigations = navigationService.getNavigations(0L, true);
        return MapUtils.success(navigations);
    }

    @Log("查询导航栏")
    @ApiOperation("查询导航栏")
    @GetMapping("/list")
    public Map<String, Object> getNavigation() {
        List<Navigation> navigations = navigationService.getNavigation(0L);
        return MapUtils.success(navigations);
    }

    @GetMapping("/tree")
    public Map<String, Object> tree() {
        List<NodeTree> trees = navigationService.getNavigationTree(0L);
        return MapUtils.success(trees);
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Validated @RequestBody NavigationRequest navigation) {
        navigationService.save(navigation.toNavigation());
        return MapUtils.success();
    }

    @PutMapping("/update")
    public Map<String, Object> update(@Validated @RequestBody NavigationRequest navigation) {
        navigationService.update(navigation.toNavigation());
        return MapUtils.success();
    }

    @DeleteMapping("/delete")
    public Map<String, Object> delete(@Validated @RequestBody @NotNull Set<Long> ids) {
        navigationService.delete(ids);
        return MapUtils.success();
    }
}
