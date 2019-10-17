package com.xzjie.cms.service.impl;

import com.xzjie.cms.WebApplicationTests;
import com.xzjie.cms.core.utils.JsonUtils;
import com.xzjie.cms.dto.MenuRouter;
import com.xzjie.cms.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class MenuServiceImplTest extends WebApplicationTests {

    @Autowired
    private MenuService menuService;

    @Test
    public void getMenuRouter() {

        List<MenuRouter> menuRouters = menuService.getMenuRouter(0L, new HashSet<>());
        System.out.printf(">>" + JsonUtils.toJsonString(menuRouters));

    }
}
