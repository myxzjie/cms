package com.xzjie.cms.log.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.log.model.SystemLog;

import java.util.List;

public interface SystemLogService extends BaseService<SystemLog> {

    List<SystemLog> getLoginSystemLog(String username);
}
