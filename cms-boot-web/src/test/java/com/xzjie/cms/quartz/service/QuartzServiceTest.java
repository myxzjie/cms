package com.xzjie.cms.quartz.service;

import com.xzjie.cms.WebApplicationTests;
import com.xzjie.cms.core.utils.JsonUtils;
import com.xzjie.cms.quartz.dto.QuartzResult;
import com.xzjie.cms.quartz.model.QuartzEntity;
import com.xzjie.cms.quartz.task.TaskJob;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class QuartzServiceTest extends WebApplicationTests {
    @Autowired
    private QuartzService quartzService;

    @Test
    public void createTask() throws Exception {
        QuartzEntity entity = new QuartzEntity();
        entity.setJobName("TaskJob");
        entity.setJobGroup("cms");
        entity.setJobClass(TaskJob.class.getName());
        entity.setCronExpression("0/5 * * * * ?");
        quartzService.createTask(entity);
    }

    @Test
    public void createEmailTask() throws Exception {
        QuartzEntity entity = new QuartzEntity();
        entity.setJobName("email_verify_code");
        entity.setJobGroup("cms");
        entity.setJobClass(TaskJob.class.getName());
        entity.setCronExpression("0/5 * * * * ?");
        quartzService.createTask(entity);
    }

    @Test
    public void getQuartzList() {
        List<QuartzResult> list = quartzService.getQuartzList();

        log.info(">>" + JsonUtils.toJsonString(list));
    }

    @Test
    public void deleteTask() throws SchedulerException {
        quartzService.deleteTask("TaskJob","DEFAULT");
    }
}
