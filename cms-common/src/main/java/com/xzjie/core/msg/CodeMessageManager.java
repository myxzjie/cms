package com.xzjie.core.msg;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xzjie on 2017/4/30.
 */
public class CodeMessageManager extends PropertyPlaceholderConfigurer {

    private static Map<String, String> propMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propMap.put(keyStr, value);
        }
    }

    public static String getMessage(String code) {
        return propMap.get(code);
    }

    public static String getCodeMsg(String code) {
        String msg="code:"+code+",msg:"+getMessage(code);
        return msg;
    }
}
