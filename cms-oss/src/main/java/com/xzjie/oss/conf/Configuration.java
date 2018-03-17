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
	private  volatile static Configuration conf;


	public class OSS {
		public final static String endpoint = "oss.endpoint";
		public final static String accessKeyId = "oss.accessKeyId";
		public final static String accessKeySecret = "oss.accessKeySecret";
		public final static String supportCname ="oss.supportCname";
		public final static String maxConnections ="oss.maxConnections";
		public final static String socketTimeout ="oss.socketTimeout";
		public final static String maxErrorRetry ="oss.maxErrorRetry";

		public final static String useCDN ="useCDN";
		private  final static String localPath="local.path";
		private final static String localURL="local.url";

		public final static String useLocalStorager ="useLocalStorager";
		public final static String useAsynUploader="useAsynUploader";
	}

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

	public static Configuration getConf(){
		if(conf==null){
			synchronized (Configuration.class){
				if(conf==null){
					conf=new Configuration();
				}
			}
		}
		return conf;
	}

	public static int getMaxErrorRetry() {
		String str = getConf().getValue(OSS.maxErrorRetry);
		return Integer.parseInt(str.trim());
	}

	public static int getSocketTimeout() {
		String str = getConf().getValue(OSS.socketTimeout);
		return Integer.parseInt(str.trim());
	}

	public static int getMaxConnections() {
		String str = getConf().getValue(OSS.maxConnections);
		return Integer.parseInt(str.trim());
	}

	public static boolean supportCname() {
		String bool = getConf().getValue(OSS.supportCname);
		return "true".equals(bool.trim());
	}

	public static String getEndPoint() {
		return getConf().getValue(OSS.endpoint);
	}

	public static String getAccessKey() {
		return getConf().getValue(OSS.accessKeyId);
	}

	public static String getAccessSecret() {
		return getConf().getValue(OSS.accessKeySecret);
	}


	public static boolean useStatus(){
		String bool = getConf().getValue(OSS.useCDN);
		return "true".equals(bool.trim());
	}

	public static boolean useLocalStorager(){
		String bool = getConf().getValue(OSS.useLocalStorager);
		return "true".equals(bool.trim());
	}

	public static boolean useAsynUploader(){
		String bool = getConf().getValue(OSS.useAsynUploader);
		return "true".equals(bool.trim());
	}

	public static String getLocalPath(){
		return getConf().getValue(OSS.localPath);
	}

	public static String getLocalURL() {
		return getConf().getValue(OSS.localURL);
	}
}
