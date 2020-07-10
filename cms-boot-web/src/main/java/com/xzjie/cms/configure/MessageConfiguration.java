package com.xzjie.cms.configure;

import com.xzjie.cms.core.utils.I18Utils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.annotation.PostConstruct;

@Configuration
public class MessageConfiguration {

    @PostConstruct
    public void init() {
        I18Utils.setMessageSource(messageSource());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("message/messages");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

//    @ConditionalOnMissingBean
//    public ReloadableResourceBundleMessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        // 用分号隔开各个语言资源路径
//        String[] paths=path.split(";");
//        messageSource.setBasenames(paths);
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setUseCodeAsDefaultMessage(true);
//        messageSource.setFallbackToSystemLocale(false);
//
//        return messageSource;
//    }
}
