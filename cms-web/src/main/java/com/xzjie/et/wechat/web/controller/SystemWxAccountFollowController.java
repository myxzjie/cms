package com.xzjie.et.wechat.web.controller;

import java.util.Map;

import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.et.wechat.service.WxAccountFollowService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.service.WxAccountService;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;

@Controller
@RequestMapping("${web.adminPath}/wx-account/follow")
public class SystemWxAccountFollowController extends BaseController {

    private final Logger LOG = LogManager.getLogger(getClass());

    @Autowired
    private WxAccountFollowService wxAccountFollowService;

    @RequestMapping(value = {"", "/", "index"})
    public String index(Map<String, Object> modelView) {
        return getRemoteView("wechat/wx_account_follow/wx_account_follow");
    }

    @RequestMapping(value = "popup")
    public String followPopup(Long groupId, ModelMap modelMap) {
        modelMap.put("groupId", groupId);
        return getRemoteView("wechat/wx_account_follow/wx_account_follow_popup");
    }

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(WxAccountFollow model, Page page) {
        PageEntity<WxAccountFollow> pageEntity = new PageEntity<WxAccountFollow>();

        model.setSiteId(getSiteId());
        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<WxAccountFollow> res = wxAccountFollowService.getListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得数据错误：{}", e);
        }
        return MapResult.mapError("601");
    }

    @RequestMapping("sync")
    @ResponseBody
    public Map<String, Object> syncFollow() {
        try {
            wxAccountFollowService.batchSyncAccountFollow(getSiteId(), "");
            return MapResult.mapOK();
        } catch (Exception e) {
            LOG.error("sync follow error.", e);
        }
        return MapResult.mapError();
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> dataPage(@PathVariable Long id) {
        try {
            wxAccountFollowService.delete(id);
            return MapResult.mapOK();
        } catch (Exception e) {
            LOG.error("wechat follow delete error.", e);
        }

        return MapResult.mapError("24");
    }

}
