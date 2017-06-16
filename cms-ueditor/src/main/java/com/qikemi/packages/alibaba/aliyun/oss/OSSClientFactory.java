package com.qikemi.packages.alibaba.aliyun.oss;

import org.apache.log4j.Logger;

import com.aliyun.openservices.oss.OSSClient;
import com.qikemi.packages.alibaba.aliyun.oss.properties.OSSClientProperties;

/**
 * OSSClient是OSS服务的Java客户端，它为调用者提供了一系列的方法，用于和OSS服务进行交互<br>
 * 
 * @author XieXianbin me@xiexianbin.cn
 */
public class OSSClientFactory {

	private static Logger logger = Logger.getLogger(OSSClientFactory.class);
	private static OSSClient client = null;
	
	/**
	 * 新建OSSClient 
	 * 
	 * @return client
	 */
	public static OSSClient createOSSClient(){
		if ( null == client){
			client = new OSSClient(OSSClientProperties.ossCliendEndPoint, OSSClientProperties.key, OSSClientProperties.secret);
			logger.info("First CreateOSSClient success.");
		}
		return client;
	}

}
