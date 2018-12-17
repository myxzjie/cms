package com.xzjie.et.wechat.web.controller;

import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2018-12-14 22:25
 **/
@Controller
@RequestMapping("${web.adminPath}/wx-article")
public class SystemWxArticleController extends BaseController {

    @Autowired
    private WxMessageService wxMessageService;

    @RequestMapping(value = {"", "/", "index"})
    public String indexView(ModelMap modelMap){
        return getRemoteView("wechat/wx_article/wx_article");
    }


    @RequestMapping("addview")
    public String editView() {
        return getRemoteView("wechat/wx_article/wx_article_edit");
    }

    @RequestMapping("edit/{id}")
    public String editView(@PathVariable Long id, Model model) {
        WxMessage message = wxMessageService.get(id);
        model.addAttribute("model", message);
        return getRemoteView("wechat/wx_article/wx_article_edit");
    }
}
