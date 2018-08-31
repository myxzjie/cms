package com.xzjie.et.system.web.controller;

import com.xzjie.et.system.model.Account;
import com.xzjie.et.system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzjie.et.core.web.BaseController;

import java.util.Map;

/**
 * Created by xzjie on 2017/8/7.
 */
@Controller
@RequestMapping("${web.adminPath}")
public class SystemDefaultController extends BaseController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = {"", "/", "index"})
    public String index(ModelMap modelMap) {
        Account account = accountService.get(getUserId());
        modelMap.put("account", account);
        return getRemoteView("index");
    }

    @RequestMapping(value = {"home"})
    public String home(ModelMap modelMap) {

        return getRemoteView("home");
    }
}
