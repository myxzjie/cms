package com.xzjie.et.wechat.task;

import com.xzjie.et.cms.model.Site;
import com.xzjie.et.cms.service.SiteService;
import com.xzjie.et.wechat.service.WxAccountFollowService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WxAccountFollowTask {

    @Autowired
    private SiteService siteService;
    @Autowired
    private WxAccountFollowService wxAccountFollowService;

    public void execute() {
        List<Site> sites = siteService.getList(new Site());

        for (Site site : sites) {
            wxAccountFollowService.batchSyncAccountFollow(site.getSiteId(),"");
        }
    }
}
