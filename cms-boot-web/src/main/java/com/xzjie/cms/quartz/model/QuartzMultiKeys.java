package com.xzjie.cms.quartz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.mapping.PrimaryKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
public class QuartzMultiKeys implements Serializable {
    private String schedName;
    private String jobName; //任务名称
    private String jobGroup; //任务分组

}
