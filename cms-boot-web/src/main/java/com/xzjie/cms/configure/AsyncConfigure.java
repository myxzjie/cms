package com.xzjie.cms.configure;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncConfigure {

    @Autowired
    private AsyncProperties properties;

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(properties.getCorePoolSize());
        // 设置最大线程数
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        // 设置队列容量
        executor.setQueueCapacity(properties.getQueueCapacity());
        // 设置线程活跃时间（秒）
//        executor.setKeepAliveSeconds(properties.);
        // 设置默认线程名称
        executor.setThreadNamePrefix(properties.getNamePrefix());

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.initialize();
        return executor;
    }
}
