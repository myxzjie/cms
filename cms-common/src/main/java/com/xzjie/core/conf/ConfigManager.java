package com.xzjie.core.conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;

/**
 * Created by asus on 2017/8/16.
 */
public class ConfigManager {
    private static final Logger LOG = LogManager.getLogger(ConfigManager.class);
    private  volatile static ConfigManager conf;
    private static String CONF_FILE="conf/config.properties";
    private static Properties prop = new Properties();

    private WeakHashMap<String, String> propMap = new WeakHashMap<String, String>();

    public ConfigManager(){
        iterator();
    }

    static {
        ClassLoader cL = Thread.currentThread().getContextClassLoader();
        if (cL == null) {
            cL = ConfigManager.class.getClassLoader();
        }
        String path = cL.getResource(CONF_FILE).getPath().trim();

        // 生成文件输入流
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(path));
            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("系统未找到指定文件：[{}]",CONF_FILE);
        }
    }

    private void iterator() {
        for (Map.Entry<Object, Object> item : prop.entrySet()) {
            if (item.getKey() instanceof String && item.getValue() instanceof String) {
                propMap.put((String) item.getKey(), (String) item.getValue());
            }
        }
    }
    public String getValue(String key){
        return propMap.get(key);
    }

    public static ConfigManager getConf(){
        if(conf==null){
            synchronized (ConfigManager.class){
                if(conf==null){
                    conf=new ConfigManager();
                }
            }
        }
        return conf;
    }
}
