package com.xzjie.cms.configure;

import com.xzjie.cms.core.utils.I18Utils;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.annotation.PostConstruct;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.valueextraction.ValueExtractor;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

@Configuration
public class MessageConfiguration {
    @Autowired
   private MessageSource messageSource;
    @PostConstruct
    public void init() {
        I18Utils.setMessageSource(messageSource);
    }

//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//        resourceBundleMessageSource.setBasename("message/messages");
//        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
//        return resourceBundleMessageSource;
//    }

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

//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        //仅兼容Spring Boot spring.messages设置的国际化文件和原hibernate-validator的国际化文件
//        //不支持resource/ValidationMessages.properties系列
//        bean.setValidationMessageSource(this.messageSource);
//        return bean;
//    }


//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator) {
//        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
//        methodValidationPostProcessor.setValidator(validator);
//        return methodValidationPostProcessor;
//    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setSupportedLocales(asList(Locale.CHINA, Locale.US));
        resolver.setDefaultLocale(Locale.CHINA);
        return resolver;
    }
}
