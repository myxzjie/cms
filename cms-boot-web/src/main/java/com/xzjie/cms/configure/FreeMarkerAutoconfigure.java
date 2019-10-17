package com.xzjie.cms.configure;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.xzjie.cms.client.freemarker.AbstractTemplateModel;
import com.xzjie.cms.client.freemarker.DefaultFreemarkerView;
import com.xzjie.cms.client.freemarker.DefaultTags;
import com.xzjie.cms.client.freemarker.annotation.FreemarkerComponent;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleDate;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@Configuration
public class FreeMarkerAutoconfigure implements ApplicationContextAware {
    private final static String TAG = "et";
    private final static String TAG_SHIRO = "shiro";
    private ApplicationContext applicationContext;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Bean
    public void afterPropertiesSet() {
        this.setSharedVariable(TAG_SHIRO, new ShiroTags());

        Map<String, Object> map = applicationContext.getBeansWithAnnotation(FreemarkerComponent.class);
        DefaultTags tags = new DefaultTags(AbstractTemplateModel.getObjectWrapper());
        map.forEach((key, value) -> tags.put(key, value));
        this.setSharedVariable(TAG, tags);
    }

    public void setSharedVariable(String tag, TemplateModel templateModel) {
        getConfiguration().setSharedVariable(tag, templateModel);
        getConfiguration().setObjectWrapper(new CustomObjectWrapper());

    }

    public freemarker.template.Configuration getConfiguration() {
        return freeMarkerConfigurer.getConfiguration();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Bean
    public ViewResolver viewResolver(FreeMarkerViewResolver resolver) {
//        resolver.setCache(true);
//        resolver.setPrefix("");
//        resolver.setSuffix(".ftl");
//        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setViewClass(DefaultFreemarkerView.class);
        return resolver;
    }

    private static class CustomObjectWrapper extends DefaultObjectWrapper {

        @Override
        public TemplateModel wrap(Object obj) throws TemplateModelException {
            if (obj instanceof LocalDateTime) {
                Timestamp timestamp = Timestamp.valueOf((LocalDateTime) obj);
                return new SimpleDate(timestamp);
            }
            if (obj instanceof LocalDate) {
                Date date = Date.valueOf((LocalDate) obj);
                return new SimpleDate(date);
            }
            if (obj instanceof LocalTime) {
                Time time = Time.valueOf((LocalTime) obj);
                return new SimpleDate(time);
            }
            return super.wrap(obj);
        }
    }

//    /**
//     * 增加自定义视图变量和方法
//     *
//     * @return
//     */
//    @Bean
//    public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                //增加视图
//                resolver.setViewClass(MyFreemarkerView.class);
//                //添加自定义解析器
//                Map map = resolver.getAttributesMap();
//                map.put("conver", new MyConver());
//            }
//        };
//    }
}
