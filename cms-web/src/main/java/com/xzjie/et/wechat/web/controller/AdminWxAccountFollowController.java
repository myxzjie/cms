package com.xzjie.et.wechat.web.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class AdminWxAccountFollowController  extends BaseController {
	
	private final Logger LOG = LogManager.getLogger(getClass());

    @Autowired
    private WxAccountService wxAccountService;

    @RequestMapping(value = {"", "/", "index"})
    public String index(Map<String, Object> modelView) {
        return getRemoteView("wechat/wx_account_follow/wx_account_follow");
    }
    
    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(WxAccount model, Page page) {
        PageEntity<WxAccount> pageEntity = new PageEntity<WxAccount>();
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

}
