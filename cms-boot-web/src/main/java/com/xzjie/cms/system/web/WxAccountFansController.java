package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.WxAccountFansRequest;
import com.xzjie.cms.model.WxAccountFans;
import com.xzjie.cms.service.WxAccountFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wx-fans")
public class WxAccountFansController {

    @Autowired
    private WxAccountFansService accountFansService;

    @GetMapping(value = "/list")
    public Map<String, Object> articleList(WxAccountFansRequest fans) {
        Page<WxAccountFans> accountFansPage = accountFansService.getAccountFans(fans.getPage(), fans.getSize(), fans.toAccountFans());
        return MapUtils.success(accountFansPage.getContent(), accountFansPage.getTotalElements());
    }

    @GetMapping(value = "/data")
//    @PreAuthorize("hasAuthority('user')")
    public Map<String, Object> getAccountFansList(WxAccountFansRequest fans) {
        List<WxAccountFans> accountFans = accountFansService.getAccountFans(fans.toAccountFans());
        return MapUtils.success(accountFans);
    }

    @PostMapping(value = "sync")
    public Map<String, Object> syncAccountFans() {
        accountFansService.syncAccountFans("");
        return MapUtils.success();
    }
}
