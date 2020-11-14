package com.xzjie.cms.client.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.model.Navigation;
import com.xzjie.cms.service.NavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/navigation")
public class AppNavigationController {

    @Autowired
    private NavigationService navigationService;

    @GetMapping("/data")
    public Map<String, Object> getNavigations() {
        List<Navigation> navigations = navigationService.getNavigations(0L, true);
        return MapUtils.success(navigations);
    }
}
