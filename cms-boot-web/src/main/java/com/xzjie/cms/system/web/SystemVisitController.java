package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.visit.service.VisitLogService;
import com.xzjie.cms.vo.ChartData;
import com.xzjie.cms.vo.VisitStatistics;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Vito
 * @since 2022/3/19 7:21 下午
 */
@RestController
@RequestMapping("/api/system")
@Api(value = "管理端-访问量管理",tags = "管理端-访问量管理")
public class SystemVisitController {

    @Autowired
    private VisitLogService visitLogService;

    @GetMapping("/visit/statistics")
    public Map<String, Object> getVisitStatistics() {
        List<VisitStatistics> statistics = visitLogService.getVisitStatistics();
        return MapUtils.success(statistics);
    }

    @GetMapping("/visit/data")
    public Map<String, Object> getVisitData() {
        ChartData data = visitLogService.getVisitData();
        return MapUtils.success(data);
    }
}
