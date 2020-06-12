package com.xzjie.cms.quartz.task;

import com.xzjie.cms.service.VerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@DisallowConcurrentExecution
public class EmailVerifyCodeJob implements Job {
    @Autowired
    private VerifyCodeService verifyCodeService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("starting execute EmailVerifyCodeJob ....");
        verifyCodeService.getVerifyCodeExpiration()
                .stream().forEach(verifyCode -> {
            try {
                verifyCode.setState(0);
                verifyCodeService.save(verifyCode);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        log.info("end EmailVerifyCodeJob ....");
    }
}
