package com.xzjie.cms.quartz.dto;

import com.xzjie.cms.quartz.model.QuartzMultiKeys;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

public interface QuartzResult {
    String getJobName(); //任务名称

    String getJobGroup(); //任务分组

    String getJobStatus(); //任务状态

    String getJobClass();//任务执行方法

    String getCronExpression(); // cron 表达式

    String getJobDescription(); //任务描述

    String getInvokeParam();//需要传递的参数

    String getTimeZoneId(); // 时区

    Long getStartTime();

    Long getEndTime();

    String getState(); //状态
}
