package com.xzjie.oss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.xzjie.oss.conf.Configuration;
import com.xzjie.oss.conf.Configuration.OSS;

public class AuthOSSClient {
	private static Logger logger = LoggerFactory.getLogger(AuthOSSClient.class);
	private Configuration conf = null;

	public AuthOSSClient() {
		if (conf == null) {
			conf = new Configuration();
		}
	}

	public AuthOSSClient(Configuration conf) {
		this.conf = conf;
	}
	
	public OSSClient create() {
		// 创建ClientConfiguration实例，按照您的需要修改默认参数
		ClientConfiguration conf = new ClientConfiguration();
		// 开启支持CNAME选项
		conf.setSupportCname(isSupportCname());
		// 设置OSSClient使用的最大连接数，默认1024
		conf.setMaxConnections(getMaxConnections());
		// 设置请求超时时间，默认50秒
		conf.setSocketTimeout(getSocketTimeout());
		// 设置失败请求重试次数，默认3次
		conf.setMaxErrorRetry(getMaxErrorRetry());

		OSSClient client = new OSSClient(getEndPoint(), getAccessKey(), getAccessSecret(), conf);
		logger.info(">>create OSSClient success.");
		return client;
	}

	public Configuration getConf() {
		return conf;
	}

	public int getMaxErrorRetry() {
		String str = getConf().getValue(OSS.maxErrorRetry);
		return Integer.parseInt(str.trim());
	}

	public int getSocketTimeout() {
		String str = getConf().getValue(OSS.socketTimeout);
		return Integer.parseInt(str.trim());
	}

	public int getMaxConnections() {
		String str = getConf().getValue(OSS.maxConnections);
		return Integer.parseInt(str.trim());
	}

	public boolean isSupportCname() {
		String bool = getConf().getValue(OSS.supportCname);
		return "true".equals(bool.trim());
	}

	public String getEndPoint() {
		return getConf().getValue(OSS.endpoint);
	}

	public String getAccessKey() {
		return getConf().getValue(OSS.accessKeyId);
	}

	public String getAccessSecret() {
		return getConf().getValue(OSS.accessKeySecret);
	}
}
