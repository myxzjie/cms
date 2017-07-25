package com.xzjie.oss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.xzjie.oss.conf.Configuration;
import com.xzjie.oss.conf.Configuration.OSS;

public class AuthOSSClient {
	private static Logger logger = LoggerFactory.getLogger(AuthOSSClient.class);

	public AuthOSSClient() {}

	public OSSClient create() {
		OSSClient client = new OSSClient(Configuration.getEndPoint(), Configuration.getAccessKey(), Configuration.getAccessSecret(), getClientConf());
		logger.info(">>create OSSClient success.");
		return client;
	}


	public  ClientConfiguration getClientConf(){
		// 创建ClientConfiguration实例，按照您的需要修改默认参数
		ClientConfiguration conf = new ClientConfiguration();
		// 开启支持CNAME选项
		conf.setSupportCname(Configuration.supportCname());
		// 设置OSSClient使用的最大连接数，默认1024
		conf.setMaxConnections(Configuration.getMaxConnections());
		// 设置请求超时时间，默认50秒
		conf.setSocketTimeout(Configuration.getSocketTimeout());
		// 设置失败请求重试次数，默认3次
		conf.setMaxErrorRetry(Configuration.getMaxErrorRetry());

		return conf;
	}


}
