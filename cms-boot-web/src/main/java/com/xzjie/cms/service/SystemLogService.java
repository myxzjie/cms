package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.SystemLog;

import java.util.List;

public interface SystemLogService extends BaseService<SystemLog,Long> {

    List<SystemLog> getLoginSystemLog(String username);
}
