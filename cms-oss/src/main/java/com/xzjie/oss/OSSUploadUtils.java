package com.xzjie.oss;

import java.io.InputStream;

import com.xzjie.oss.stream.ObjectOSSUpload;

public class OSSUploadUtils {

	private static ObjectOSSUpload ossUpload = null;
	private static String bucketName = "b-cdn";

	public static String upload(String dir, String suffix, InputStream stream) {

		if (ossUpload == null) {
			ossUpload = new ObjectOSSUpload();
		}

		FileInfoBuilder builder = new FileInfoBuilder(dir, suffix);

		String key = builder.getPath();
		ossUpload.setBucketName(bucketName);
		ossUpload.setKey(key);

		return ossUpload.upload(stream);

	}
}
