package com.xzjie.cms.system.web;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "管理端-默认管理",tags = "管理端-默认管理")
public class SystemDefaultController  {

    @GetMapping("/system/home")
    @PreAuthorize("hasPermission('admin,/user/user/list','user:user:list')")
    public String home() {
        return "hi";
    }

}
