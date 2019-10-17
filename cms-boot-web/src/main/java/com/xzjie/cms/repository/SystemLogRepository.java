package com.xzjie.cms.repository;

import com.xzjie.cms.model.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SystemLogRepository extends JpaRepository<SystemLog, Long>, JpaSpecificationExecutor {

    List<SystemLog> findByNameAndUsername(String name, String username);
}
