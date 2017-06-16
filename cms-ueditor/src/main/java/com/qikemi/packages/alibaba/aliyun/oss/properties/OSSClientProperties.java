package com.qikemi.packages.alibaba.aliyun.oss.properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.qikemi.packages.utils.SystemUtil;

public class OSSClientProperties {

	private static Logger logger = Logger.getLogger(OSSClientProperties.class);
	
	private static Properties OSSKeyProperties = new Properties();
	// 阿里云是否启用配置
	public static boolean useStatus = false;
	public static String bucketName = "";
	public static String key = "";
	public static String secret = "";
	public static boolean autoCreateBucket = false;
	
	public static String ossCliendEndPoint = "";
	public static String ossEndPoint = "";
	public static boolean useCDN = false;
	public static String cdnEndPoint = "";
	
	public static boolean useLocalStorager = false;
	public static String uploadBasePath = "upload";
	public static boolean useAsynUploader = false;

	static {
		String OSSKeyPath = SystemUtil.getProjectClassesPath()
				+ "OSSKey.properties";
		// 生成文件输入流
		FileInputStream inpf = null;
		try {
			inpf = new FileInputStream(new File(OSSKeyPath));
			OSSKeyProperties.load(inpf);
			
			useStatus = "true".equalsIgnoreCase((String) OSSKeyProperties.get("useStatus")) ? true : false;
			bucketName = (String) OSSKeyProperties.get("bucketName");
			key = (String) OSSKeyProperties.get("key");
			secret = (String) OSSKeyProperties.get("secret");
			autoCreateBucket = "true".equalsIgnoreCase((String) OSSKeyProperties.get("autoCreateBucket")) ? true : false;
			
			ossCliendEndPoint = (String) OSSKeyProperties.get("ossCliendEndPoint");
			ossEndPoint = (String) OSSKeyProperties.get("ossEndPoint");
			useCDN = "true".equalsIgnoreCase((String) OSSKeyProperties.get("useCDN")) ? true : false;
			cdnEndPoint = (String) OSSKeyProperties.get("cdnEndPoint");
			
			useLocalStorager = "true".equalsIgnoreCase((String) OSSKeyProperties.get("useLocalStorager")) ? true : false;
			uploadBasePath = (String) OSSKeyProperties.get("uploadBasePath");
			useAsynUploader = "true".equalsIgnoreCase((String) OSSKeyProperties.get("useAsynUploader")) ? true : false;
		} catch (Exception e) {
			logger.warn("系统未找到指定文件：OSSKey.properties --> 系统按照ueditor默认配置执行。");
		}
	}

}
