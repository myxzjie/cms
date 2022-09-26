package com.xzjie.cms.log.repository;

import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.log.model.SystemLog;

import java.util.List;

public interface SystemLogRepository extends BaseRepository<SystemLog, Long> {

    List<SystemLog> findByNameAndUsername(String name, String username);
}
