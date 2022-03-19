package com.xzjie.cms.client.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.RequestHolder;
import com.xzjie.cms.model.VisitLog;
import com.xzjie.cms.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Vito
 * @since 2022/3/18 11:04 下午
 */
@RestController
@RequestMapping("/app/visit")
public class AppVisitLogController {
    @Autowired
    private VisitLogService visitLogService;

    @PostMapping("/save")
    public Map<String, Object> createVisit(@RequestBody VisitLog model) {
        model.setIp(RequestHolder.getIp());
        visitLogService.save(model);
        return MapUtils.success();
    }
}
