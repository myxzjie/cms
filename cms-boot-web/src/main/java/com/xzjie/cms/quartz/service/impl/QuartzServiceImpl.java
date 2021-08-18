package com.xzjie.cms.quartz.service.impl;

import com.xzjie.cms.quartz.dto.QuartzResult;
import com.xzjie.cms.quartz.model.QuartzEntity;
import com.xzjie.cms.quartz.repository.QuartzRepository;
import com.xzjie.cms.quartz.service.QuartzService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    @Qualifier("quartzScheduler")
    private Scheduler scheduler;
    @Autowired
    private QuartzRepository quartzRepository;

    @Override
    public boolean createTask(QuartzEntity quartz) throws Exception {
        String jobName = quartz.getJobName(),
                jobGroup = quartz.getJobGroup(),
                cronExpression = quartz.getCronExpression(),
                jobDescription = quartz.getJobDescription();
        try {
            if (checkExists(jobName, jobGroup)) {
                throw new Exception(String.format("Job已经存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(cronExpression)
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .withDescription(jobDescription)
                    .withSchedule(scheduleBuilder)
                    .build();

            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(quartz.getJobClass());
            JobDetail jobDetail = JobBuilder
                    .newJob(clazz)
                    .withIdentity(jobKey)
                    .withDescription(jobDescription).build();
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException | ClassNotFoundException e) {
            throw new Exception("类名不存在或执行表达式错误");
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass
     *            任务实现类
     * @param jobName
     *            任务名称
     * @param jobGroupName
     *            任务组名
     * @param jobTime
     *            时间表达式 (这是每隔多少秒为一次任务)
     * @param jobTimes
     *            运行的次数 （<0:表示不限次数）
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String jobGroupName, int jobTime,
                       int jobTimes) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName)// 任务名称和组构成任务key
                    .build();
            // 使用simpleTrigger规则
            Trigger trigger = null;
            if (jobTimes < 0) {
                trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withIntervalInSeconds(jobTime))
                        .startNow().build();
            } else {
                trigger = TriggerBuilder
                        .newTrigger().withIdentity(jobName, jobGroupName).withSchedule(SimpleScheduleBuilder
                                .repeatSecondlyForever(1).withIntervalInSeconds(jobTime).withRepeatCount(jobTimes))
                        .startNow().build();
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始定时任务
     *
     * @param quartz
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean resumeTask(QuartzEntity quartz) {
        try {
            scheduler.resumeJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 立即执行一个job
     *
     * @param quartz
     */
    public boolean runJobNow(QuartzEntity quartz) {
        try {
            JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.triggerJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * @return
     * @// TODO: 2018/6/5  查询job
     */
    @Override
    public List<QuartzResult> getQuartzList() {
        return quartzRepository.findQuartz();
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    public List<QuartzEntity> queryAllJob() {
        List<QuartzEntity> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    QuartzEntity entity = new QuartzEntity();
                    entity.setJobName(jobKey.getName());
                    entity.setJobGroup(jobKey.getGroup());
                    entity.setJobDescription("触发器:" + trigger.getKey());

//                    Map<String, Object> map = new HashMap<>();
//                    map.put("jobName", jobKey.getName());
//                    map.put("jobGroupName", jobKey.getGroup());
//                    map.put("description", "触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
//                    map.put("jobStatus", triggerState.name());
                    entity.setJobStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
//                        map.put("jobTime", cronExpression);
                        entity.setCronExpression(cronExpression);
                    }
                    jobList.add(entity);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    /**
     * 获取所有正在运行的job
     *
     * @return
     */
    public List<Map<String, Object>> queryRunJob() {
        List<Map<String, Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;

    }

    /**
     * 修改定时任务
     *
     * @param quartz
     */
    @Override
    public boolean updateTask(QuartzEntity quartz) throws Exception {
        String jobName = quartz.getJobName(),
                jobGroup = quartz.getJobGroup(),
                cronExpression = quartz.getCronExpression(),
                jobDescription = quartz.getJobDescription();
//                createTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (!checkExists(jobName, jobGroup)) {
                throw new Exception(String.format("Job不存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(cronScheduleBuilder);
            if (StringUtils.isNotBlank(jobDescription)) {
                triggerBuilder.withDescription(jobDescription);
            }
            CronTrigger cronTrigger = triggerBuilder.build();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            Set<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
            return true;
        } catch (SchedulerException e) {
            throw new Exception("类名不存在或执行表达式错误");
        }
    }

    /**
     * @param quartz
     * @// TODO: 2018/6/1 停止任务
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean pauseTask(QuartzEntity quartz) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
        try {
            if (checkExists(quartz.getJobName(), quartz.getJobGroup())) {
                scheduler.pauseTrigger(triggerKey); //停止触发器
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 删除某个任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    @Override
    public String deleteTask(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return "jobDetail is null";
        } else if (!scheduler.checkExists(jobKey)) {
            return "jobKey is not exists";
        } else {
            scheduler.deleteJob(jobKey);
            return "success";
        }

    }

    /**
     * 验证是否存在
     *
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    private boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}
