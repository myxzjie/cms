package com.xzjie.et.wechat.web.controller;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.cms.model.Article;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.wechat.entity.WxArticleModel;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxMessageService;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
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
    public String indexView(ModelMap modelMap) {
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

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(WxMessage model, Page page) {
        PageEntity<WxMessage> pageEntity = new PageEntity<WxMessage>();
        Map<String,Object> map =new HashMap<>();
        model.setSiteId(getSiteId());
        model.setpId(0L);

        map.put("type",2);

        pageEntity.setT(model);
        pageEntity.setPage(page);
        pageEntity.setMap(map);
        try {
            PageEntity<WxMessage> res = wxMessageService.getMessageListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得数据错误.", e);
        }
        return MapResult.mapError("601");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(WxArticleModel articles) {

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
            wxMessageService.batchSave(getSiteId(), getUserId(), articles.getMessages());
            return MapResult.mapOK();
        } catch (Exception e) {
            LOG.error("add wechat news error", e);
        }

        return MapResult.mapError("24");
    }
}
