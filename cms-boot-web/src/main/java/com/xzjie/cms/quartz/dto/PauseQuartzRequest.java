package com.xzjie.cms.quartz.dto;

import com.xzjie.cms.quartz.model.QuartzEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class PauseQuartzRequest {
    @NotBlank
    private String jobName; //任务名称
    @NotBlank
    private String jobGroup; //任务分组

    public QuartzEntity toQuartzEntity() {
        QuartzEntity model = new QuartzEntity();
        BeanUtils.copyProperties(this, model);
        return model;
    }
}
