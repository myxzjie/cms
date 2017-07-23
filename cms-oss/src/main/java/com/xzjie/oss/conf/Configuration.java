package com.xzjie.oss.conf;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Configuration {

	private static Logger logger = LoggerFactory.getLogger(Configuration.class);

	private static Properties prop = new Properties();
	
	private WeakHashMap<String, String> propMap = new WeakHashMap<String, String>();
	
	public Configuration(){
		iterator();
	}
	
	static {
		ClassLoader cL = Thread.currentThread().getContextClassLoader();
		if (cL == null) {
			cL = Configuration.class.getClassLoader();
		}
		String path = cL.getResource("oss.properties").getPath().trim();
		logger.debug(">> loading {}",path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(path));
			prop.load(fis);
		} catch (Exception e) {
			logger.error("系统未找到指定文件：oss.properties");
		}
	}
	
	public String getValue(String key){
		return propMap.get(key);
	}
	
	private void iterator() {
		for (Map.Entry<Object, Object> item : prop.entrySet()) {
			if (item.getKey() instanceof String && item.getValue() instanceof String) {
				propMap.put((String) item.getKey(), (String) item.getValue());
			}
		}
	}
	
	public class OSS {
		public final static String endpoint = "oss.endpoint";
		public final static String accessKeyId = "oss.accessKeyId";
		public final static String accessKeySecret = "oss.accessKeySecret";
		public final static String supportCname ="oss.supportCname";
		public final static String maxConnections ="oss.maxConnections";
		public final static String socketTimeout ="oss.socketTimeout";
		public final static String maxErrorRetry ="oss.maxErrorRetry";
	}
	
	
}
