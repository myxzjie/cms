package com.xzjie.cms.quartz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.mapping.PrimaryKey;

import javax.persistence.*;

@Data
@Entity
@IdClass(QuartzMultiKeys.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "QRTZ_JOB_DETAILS")
@EqualsAndHashCode
public class QuartzEntity {
    //    private Long id; //ID
    @Id
    private String schedName;
    @Id
    private String jobName; //任务名称
    @Id
    private String jobGroup; //任务分组
    @Transient
    private String jobStatus; //任务状态
    @Transient
    private String jobClass;//任务执行方法
    @Transient
    private String cronExpression; // cron 表达式
    @Transient
    private String jobDescription; //任务描述
    @Transient
    private String invokeParam;//需要传递的参数
    @Transient
    private String timeZoneId; // 时区
    @Transient
    private Long startTime;
    @Transient
    private Long endTime;
    @Transient
    private String state; //状态
}
