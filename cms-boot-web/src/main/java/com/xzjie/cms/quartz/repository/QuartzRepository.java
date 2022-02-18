package com.xzjie.cms.quartz.repository;

import com.xzjie.cms.quartz.dto.QuartzResult;
import com.xzjie.cms.quartz.model.QuartzEntity;
import com.xzjie.cms.quartz.model.QuartzMultiKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuartzRepository extends JpaRepository<QuartzEntity, QuartzMultiKeys>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value = "SELECT\n" +
            "        QRTZ_JOB_DETAILS.JOB_NAME AS jobName,\n" +
            "        QRTZ_JOB_DETAILS.JOB_GROUP AS jobGroup,\n" +
            "        QRTZ_JOB_DETAILS.JOB_CLASS_NAME AS jobClass,\n" +
            "        QRTZ_TRIGGERS.DESCRIPTION AS jobDescription,\n" +
            "        QRTZ_TRIGGERS.TRIGGER_NAME AS triggerName,\n" +
            "        QRTZ_TRIGGERS.TRIGGER_GROUP AS triggerGroup,\n" +
            "        QRTZ_CRON_TRIGGERS.CRON_EXPRESSION AS cronExpression,\n" +
            "        QRTZ_TRIGGERS.START_TIME AS startTime,\n" +
            "        QRTZ_TRIGGERS.END_TIME AS endTime,\n" +
            "        QRTZ_TRIGGERS.TRIGGER_STATE AS jobStatus,\n" +
            "        QRTZ_CRON_TRIGGERS.TIME_ZONE_ID AS timeZoneId\n" +
            "        FROM\n" +
            "        QRTZ_JOB_DETAILS\n" +
            "        JOIN QRTZ_TRIGGERS\n" +
            "        JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME\n" +
            "        AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME\n" +
            "        AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP")
    List<QuartzResult> findQuartz();
}
