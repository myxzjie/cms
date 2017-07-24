package com.xzjie.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

import net.sf.json.JSONObject;

public class OSSUploadTest {

	@Test
	public void upload(){
		OSSClient client=OSSClientFactory.create();
		
		String bucketName="b-cdn";
		String path="/opt/001.jpg";
		PutObjectResult result=client.putObject(bucketName, "cms"+path, new File(path));
		JSONObject json = JSONObject.fromObject(result);
		
		System.out.println(json.toString());
		
		// 列举bucket
//		List<Bucket> buckets = client.listBuckets();
//		for (Bucket bucket : buckets) {
//		    System.out.println(" - " + bucket.getName());
//		}
		
		
		client.shutdown();
	}
	
	@Test
	public void OSSUpload() throws NumberFormatException, IOException {

		String path = "/opt/001.jpg";

		File file = new File(path);
		InputStream stream = new FileInputStream(file);
		String url = OSSUploadUtils.upload("test", path.substring(path.lastIndexOf(".")), stream);
		System.out.println(url);
	}

	@Test
	public void list(){
		List<String> list=OSSUploadUtils.list("cms/");

		for (String value : list) {
			System.out.println(">>"+value);
		}
	}
}
