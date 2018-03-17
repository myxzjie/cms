package com.xzjie.et.cms.web.controller;

import com.xzjie.et.cms.model.Site;
import com.xzjie.et.cms.service.SiteService;
import com.xzjie.common.web.utils.MapResult;
import com.xzjie.et.core.web.BaseController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by xzjie on 2017/9/21.
 */
@Controller
@RequestMapping("${web.adminPath}/site")
public class AdminSiteController extends BaseController {
    private final Logger LOG = LogManager.getLogger(AdminSiteController.class);
    @Autowired
    private SiteService siteService;


    @RequestMapping("info")
    public String info(Model model) {
        Site site = siteService.get(getSiteId());

        model.addAttribute("model", site);

        return getRemoteView("cms/site/site_info");
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Map<String, Object> update(Site model) {
        try {
            if (siteService.update(model)) {
//                getRedisService().remove(ConstantsUtils.SITE_ID_KEY+model.getSiteId());
                return MapResult.mapOK();
            }
        } catch (Exception e) {
            LOG.error("修改站点信息错误:{}", e.getMessage());
        }
        return MapResult.mapError("1101");
    }
}
