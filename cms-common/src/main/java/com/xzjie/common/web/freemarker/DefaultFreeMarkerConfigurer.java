package com.xzjie.common.web.freemarker;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.xzjie.common.annotation.freemarker.FreemarkerComponent;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jagregory.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateException;

/**
 * Created by xzjie on 2017/7/8.
 */

public class DefaultFreeMarkerConfigurer extends FreeMarkerConfigurer implements ApplicationContextAware {
	
    private final Logger LOG = LogManager.getLogger(getClass());
    
    private final String DEFAULT_TAG_NAME ="et";

    private String defaultTagName = null;
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());        


        Map<String, Object> map = applicationContext.getBeansWithAnnotation(FreemarkerComponent.class);
        DefaultTags tags=new DefaultTags();
//        Configuration configuration = this.getConfiguration();
        for (String key : map.keySet()) {
//            configuration.setSharedVariable(key, map.get(key));
            tags.put(key,map.get(key));
        }
        this.getConfiguration().setSharedVariable(getDefaultTagName(),tags);
    }    



    public String getDefaultTagName() {
    	if(defaultTagName==null||defaultTagName ==""){
    		return DEFAULT_TAG_NAME;
    	}
		return defaultTagName;
	}

	public void setDefaultTagName(String defaultTagName) {
		this.defaultTagName = defaultTagName;
	}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
