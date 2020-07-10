package com.xzjie.cms.quartz.service;

import com.xzjie.cms.quartz.dto.QuartzResult;
import com.xzjie.cms.quartz.model.QuartzEntity;
import org.quartz.SchedulerException;

import java.util.List;

public interface QuartzService {
    boolean createTask(QuartzEntity quartz) throws Exception;

    boolean resumeTask(QuartzEntity quartz);

    boolean runJobNow(QuartzEntity quartz);

    boolean updateTask(QuartzEntity quartz) throws Exception;

    boolean pauseTask(QuartzEntity quartz) throws Exception;

    String deleteTask(String jobName, String jobGroup) throws SchedulerException;

    List<QuartzResult> getQuartzList();

}
