package com.xzjie.et.wechat.web.controller;

import com.xzjie.cache.ehcache.service.SystemCacheManager;
import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.utils.RegexUtils;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.system.model.Account;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.model.WxButton;
import com.xzjie.et.wechat.service.WxAccountService;
import com.xzjie.et.wechat.service.WxButtonService;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("${web.adminPath}/wx-button")
public class AdminWxButtonController extends BaseController {

    private final Logger LOG = LogManager.getLogger(getClass());

    @Autowired
    private WxButtonService wxButtonService;


    @RequestMapping(value = {"", "/", "index"})
    public String indexView() {
        return getRemoteView("wechat/wx_button/wx_button_index");
    }

    @RequestMapping("addview")
    public String editView() {
        return getRemoteView("wechat/wx_button/wx_button_edit");
    }

    @RequestMapping("edit/{id}")
    public String editView(@PathVariable Long id, Model model) {
        WxButton button = wxButtonService.get(id);
        model.addAttribute("model", button);
        return getRemoteView("wechat/wx_button/wx_button_edit");
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(WxButton model) {
        if (StringUtils.isBlank(model.getName())) {
            return MapResult.mapError("1401");
        }

        if (StringUtils.isBlank(model.getButtonKey())) {
            return MapResult.mapError("1402");
        }

        if (StringUtils.isBlank(model.getType())) {
            return MapResult.mapError("1403");
        }

        if (StringUtils.isBlank(model.getUrl())) {
            return MapResult.mapError("1404");
        }

        if (model.getOrders() == null) {
            return MapResult.mapError("1405");
        }

        try {
            model.setSiteId(getSiteId());
            model.setCreateDate(new Date());
            if (model.getpId() == null) {
                model.setpId(0L);
            }
            wxButtonService.save(model);
            return MapResult.mapOK("1400");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("添加公众菜单错误：{}", e.getMessage());
        }

        return MapResult.mapError("1406");
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Map<String, Object> update(WxButton model) {

        if (StringUtils.isBlank(model.getName())) {
            return MapResult.mapError("1401");
        }

        if (StringUtils.isBlank(model.getButtonKey())) {
            return MapResult.mapError("1402");
        }

        if (StringUtils.isBlank(model.getType())) {
            return MapResult.mapError("1403");
        }

        if (StringUtils.isBlank(model.getUrl())) {
            return MapResult.mapError("1404");
        }

        try {
            if (wxButtonService.update(model)) {

                return MapResult.mapOK("1407");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("修改公众号菜单错误:{}", e.getMessage());
        }
        return MapResult.mapError("1408");
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> dataPage(@PathVariable Long id) {
        try {
            wxButtonService.delete(id);
            return MapResult.mapOK("1410");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("公众号菜单删除错误:{}", e.getMessage());
        }

        return MapResult.mapError("1409");
    }

    @RequestMapping(value = "sync")
    @ResponseBody
    public Map<String, Object> syncButton() {
        try {
            wxButtonService.syncButton(getSiteId());
            return MapResult.mapOK("1411");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("公众号菜单同步错误:{}", e.getMessage());
        }

        return MapResult.mapError("1412");
    }

    @RequestMapping(value = "datapage")
    @ResponseBody
    public Map<String, Object> dataPage(WxButton model, Page page) {
        PageEntity<WxButton> pageEntity = new PageEntity<>();

        model.setSiteId(getSiteId());

        pageEntity.setT(model);
        pageEntity.setPage(page);
        PageEntity<WxButton> res = wxButtonService.getListPage(pageEntity);
        return MapResult.bootPage(res.getRows(), res.getPage());
    }

    @RequestMapping(value = "tree", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> tree(WxButton model) {
        List<WxButton> buttons = wxButtonService.getTree(null, null);
        return MapResult.mapOK(buttons);
    }
}
