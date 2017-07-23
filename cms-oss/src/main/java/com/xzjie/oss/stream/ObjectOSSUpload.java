package com.xzjie.oss.stream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import net.sf.json.JSONObject;


public class ObjectOSSUpload extends DefaultOSSUpload{

	private static Logger LOG = LoggerFactory.getLogger(ObjectOSSUpload.class);
	
	public ObjectOSSUpload(){
		ossUpload = new UploadFileImpl();
	}
	
	public ObjectOSSUpload(String bucketName){
		this();
		setBucketName(bucketName);
	}
	
	@Override
	public String upload(InputStream stream)  {
		ossUpload.upload(stream);
		String url=getDomain();
		close();
		return url;
	}
	
	@Override
	public String getDomain() {
		// 设置URL过期时间为1小时
		Date expiration = new Date(new Date().getTime() + 3600 * 1000);
		// 生成URL
		URL url = client.generatePresignedUrl(getBucketName(), getKey(), expiration);

		return url.toString().substring(0, url.toString().indexOf("?"));
	}
	
	private class UploadFileImpl implements OSSUpload {
		@Override
		public void upload(InputStream stream) {
			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			try {
				// 必须设置ContentLength
				meta.setContentLength((long) stream.available());
				// 用户自定义文件名称
				meta.addUserMetadata("filename", getKey());
				PutObjectResult result = client.putObject(getBucketName(), getKey(), stream);
				LOG.info(">> put result:{}", JSONObject.fromObject(result).toString());
			} catch (IOException e) {
				e.printStackTrace();
				LOG.error("上传OSS错误:{}", e.getMessage());
			}
		}
	}

	

}
