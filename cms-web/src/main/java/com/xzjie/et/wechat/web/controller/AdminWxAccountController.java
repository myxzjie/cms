package com.xzjie.et.wechat.web.controller;

import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.service.WxAccountService;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;

@Controller
@RequestMapping("${web.adminPath}/wx-account")
public class AdminWxAccountController extends BaseController {
    private final Logger LOG = LogManager.getLogger(getClass());

    @Autowired
    private WxAccountService wxAccountService;

    @RequestMapping(value = {"", "/", "index"})
    public String index(Map<String, Object> modelView) {
        return getRemoteView("wechat/wx_account/wx_account_index");
    }


    @RequestMapping("addview")
    public String authView(Map<String, Object> modelView) {
        return getRemoteView("wechat/wx_account/wx_account_edit");
    }

    @RequestMapping(value = "editview")
    public String editView(Model model) {
        WxAccount account = wxAccountService.getWxAccountBySiteId(getSiteId());

        model.addAttribute("model", account);
        return getRemoteView("wechat/wx_account/wx_account_edit");
    }

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(WxAccount model, Page page) {
        PageEntity<WxAccount> pageEntity = new PageEntity<WxAccount>();

        model.setSiteId(getSiteId());

        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<WxAccount> res = wxAccountService.getListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得数据错误：{}", e.getMessage());
        }
        return MapResult.mapError("601");
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(WxAccount model) {
        if (StringUtils.isBlank(model.getName())) {
            return MapResult.mapError("1301");
        }

        if (StringUtils.isBlank(model.getCode())) {
            return MapResult.mapError("1302");
        }

        if (StringUtils.isBlank(model.getToken())) {
            return MapResult.mapError("1303");
        }

        if (model.getStype() == null) {
            return MapResult.mapError("1304");
        }

        if (StringUtils.isBlank(model.getEmail())) {
            return MapResult.mapError("1305");
        }

        if (StringUtils.isBlank(model.getAppid())) {
            return MapResult.mapError("1306");
        }

        if (StringUtils.isBlank(model.getAppsecret())) {
            return MapResult.mapError("1307");
        }

        try {
            if(wxAccountService.existByName(model.getName())){
                return MapResult.mapError("1300");
            }
            if(wxAccountService.existByCode(model.getCode())){
                return MapResult.mapError("1310");
            }
            model.setUserId(getUserId());
            model.setSiteId(getSiteId());
            model.setState(1);
            model.setCreateDate(new Date());
            model.setUrl("/wechat/" + model.getCode());
            if (wxAccountService.save(model)) {
                return MapResult.mapOK("1308");
            }

        } catch (Exception e) {
            LOG.error("添加公众帐号错误：{}", e.getMessage());
        }

        return MapResult.mapError("1309");
    }

    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(WxAccount model) {
        if (StringUtils.isBlank(model.getName())) {
            return MapResult.mapError("1301");
        }

        if (StringUtils.isBlank(model.getCode())) {
            return MapResult.mapError("1302");
        }

        if (StringUtils.isBlank(model.getToken())) {
            return MapResult.mapError("1303");
        }

        if (model.getStype() == null) {
            return MapResult.mapError("1304");
        }

        if (StringUtils.isBlank(model.getEmail())) {
            return MapResult.mapError("1305");
        }

        if (StringUtils.isBlank(model.getAppid())) {
            return MapResult.mapError("1306");
        }

        if (StringUtils.isBlank(model.getAppsecret())) {
            return MapResult.mapError("1307");
        }

        try {
            model.setUserId(getUserId());
            model.setSiteId(getSiteId());
            model.setState(1);
            model.setCreateDate(new Date());
            model.setUrl("/wechat/" + model.getCode());
            if (wxAccountService.update(model)) {
                return MapResult.mapOK("1311");
            }

        } catch (Exception e) {
            LOG.error("修改公众帐号错误：{}", e);
        }

        return MapResult.mapError("1312");
    }
}
