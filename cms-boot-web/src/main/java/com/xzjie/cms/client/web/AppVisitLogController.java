package com.xzjie.cms.client.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.core.utils.RequestHolder;
import com.xzjie.cms.visit.model.VisitLog;
import com.xzjie.cms.visit.service.VisitLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Vito
 * @since 2022/3/18 11:04 下午
 */
@RestController
@RequestMapping("/app/visit")
@Api(value = "前端-访问记录管理", tags = "前端-访问记录管理")
public class AppVisitLogController {
    @Autowired
    private VisitLogService visitLogService;

    @PostMapping("/save")
    public Map<String, Object> createVisit(@RequestBody VisitLog model) {
        model.setIp(RequestHolder.getIp());
        model.setPvDate(LocalDateTime.now());
        visitLogService.save(model);
        return MapUtils.success();
    }
}
