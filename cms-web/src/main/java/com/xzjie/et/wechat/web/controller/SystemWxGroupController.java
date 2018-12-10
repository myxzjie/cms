package com.xzjie.et.wechat.web.controller;

import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.wechat.model.WxGroup;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxGroupService;
import com.xzjie.et.wechat.service.WxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2018-12-09 20:31
 **/
@Controller
@RequestMapping("${web.adminPath}/wx-group")
public class SystemWxGroupController extends BaseController {

    @Autowired
    private WxMessageService wxMessageService;
    @Autowired
    private WxGroupService wxGroupService;

    @RequestMapping("view/{id}")
    public String groupView(@PathVariable Long id, Model model) {
        WxMessage message = wxMessageService.get(id);
        List<WxGroup> groups = wxGroupService.getList(new WxGroup());
        model.addAttribute("model", message);
        model.addAttribute("groups", groups);
        return getRemoteView("wechat/wx_group/wx_group");
    }

    @RequestMapping("addview")
    public String addview(Model model) {
        return getRemoteView("wechat/wx_group/wx_group_edit");
    }
}
