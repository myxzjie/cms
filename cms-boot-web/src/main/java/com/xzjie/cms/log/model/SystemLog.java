package com.xzjie.cms.log.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzjie.cms.core.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@Table(name = "sys_system_log")
public class SystemLog extends BaseEntity<SystemLog> {
    /**
     * 主键
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String name;

    private String address;
    /**
     * 操作IP
     */
    private String requestIp;

    /**
     * 操作类型 1 操作记录 2异常记录
     */
    private Integer type;

    private String level;

    /**
     * 操作人ID
     */
    private String username;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求url
     */
    private String url;

    /**
     * 请求参数
     */
    @Column(name = "params",columnDefinition = "text")
    private String params;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 开始时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 完成时间
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishTime;

    /**
     * 消耗时间
     */
    private Long time;

    /**
     * 异常详情信息 堆栈信息
     */
    @Column(name = "exception_detail",columnDefinition = "text")
    private String exceptionDetail;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @Override
    public void copy(SystemLog obj) {

    }
}
