package com.xzjie.et.system.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzjie.et.core.web.BaseController;

import java.util.Map;

/**
 * Created by xzjie on 2017/8/7.
 */
@Controller
@RequestMapping("${web.adminPath}")
public class AdminDefaultController extends BaseController{

    @RequestMapping(value={"","/","index"})
    public String index(Map<String, Object> modelView){

        return getRemoteView("index");
    }

    @RequestMapping(value={"home"})
    public String home(Map<String, Object> modelView){

        return "home";
    }
}
