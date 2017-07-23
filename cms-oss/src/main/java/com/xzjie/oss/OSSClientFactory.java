package com.xzjie.oss;

import com.aliyun.oss.OSSClient;

public class OSSClientFactory {
	
	private static OSSClient client = null;

	public static OSSClient create() {
		if(client==null){
			AuthOSSClient authOSSClient = new AuthOSSClient();
			client = authOSSClient.create();
		}
		return client;
	}

	

	

	
}
