package com.xzjie.cms.system.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemDefaultController  {

    @GetMapping("/system/home")
    @PreAuthorize("hasPermission('admin,/user/user/list','user:user:list')")
    public String home() {
        return "hi";
    }

}
