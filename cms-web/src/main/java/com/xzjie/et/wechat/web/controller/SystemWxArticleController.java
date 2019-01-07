package com.xzjie.et.wechat.web.controller;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.cms.model.Article;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2018-12-14 22:25
 **/
@Controller
@RequestMapping("${web.adminPath}/wx-article")
public class SystemWxArticleController extends BaseController {
    private final Logger LOG = LogManager.getLogger(getClass());
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

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(List<WxMessage> models) {

//        if(StringUtils.isBlank(model.getTitle())){
//            return MapResult.mapError("1002");
//        }
//        if (model.getCategoryId() == null) {
//            return MapResult.mapError("1003");
//        }
//
//        if (model.getSort() == null) {
//            model.setSort(1);
//        }
//        model.setCreateDate(new Date());
//        model.setStatus(1);
//        model.setSiteId(getSiteId());
//        model.setAuthor(getUserId());
        try {

//            articleService.save(model);
            return MapResult.mapOK();
        } catch (Exception e) {
            LOG.error("add wechat message error", e);
        }

        return MapResult.mapError("24");
    }
}
