package com.xzjie.cms.client.web;

import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.navigation.model.Navigation;
import com.xzjie.cms.navigation.service.NavigationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/navigation")
@Api(value = "前端-导航栏管理", tags = "前端-导航栏管理")
public class AppNavigationController {

    @Autowired
    private NavigationService navigationService;

    @GetMapping("/data")
    public Result<List<Navigation>> getNavigations() {
        List<Navigation> navigations = navigationService.getNavigations(0L, true);
        return Result.data(navigations);
    }
}
