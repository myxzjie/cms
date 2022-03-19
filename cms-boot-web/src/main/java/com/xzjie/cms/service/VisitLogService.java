package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.VisitLog;
import com.xzjie.cms.vo.ChartData;
import com.xzjie.cms.vo.VisitStatistics;

import java.util.List;

/**
 * @author Vito
 * @since 2022/3/19 1:29 下午
 */
public interface VisitLogService extends BaseService<VisitLog> {

    List<VisitStatistics> getVisitStatistics();

    ChartData getVisitData();
}
