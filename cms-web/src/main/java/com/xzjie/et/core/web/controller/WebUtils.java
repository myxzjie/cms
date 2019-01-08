package com.xzjie.et.core.web.controller;

import com.xzjie.et.VersionInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebUtils {

	private static Long uploadFileMaxSize;
	private static String uploadFileExts;
	private static String uploadDirectory;
	private  static String uploadFileURL;
	private static  String uploadPathFormat;

	private static String version;
	
	private WebUtils() {
		version= VersionInfo.getVersion();
	}



	@Value(value = "${upload.file.maxSize}")
	public void setUploadFileMaxSize(Long uploadFileMaxSize) {
		WebUtils.uploadFileMaxSize = uploadFileMaxSize;
	}

	public static Long getUploadFileMaxSize() {
		return uploadFileMaxSize;
	}

	@Value(value = "${upload.file.exts}")
	public void setUploadFileExts(String uploadFileExts){
		WebUtils.uploadFileExts=uploadFileExts;
	}

	public static String getUploadFileExts(){
		return uploadFileExts;
	}

	@Value(value = "${upload.directory}")
	public void setUploadDirectory(String uploadDirectory){
		WebUtils.uploadDirectory=uploadDirectory;
	}

	public static String getUploadDirectory(){
		return  uploadDirectory;
	}

	@Value(value = "${upload.file.URL}")
	public void setUploadFileURL(String uploadFileURL){
		WebUtils.uploadFileURL=uploadFileURL;
	}

	public static String getUploadFileURL(){
		return uploadFileURL;
	}


	public static String getUploadPathFormat() {
		return uploadPathFormat;
	}

	@Value(value = "${upload.path.format}")
	public void setUploadPathFormat(String uploadPathFormat) {
		WebUtils.uploadPathFormat = uploadPathFormat;
	}

	/**
	 * URL 转为文件物理路径
	 * @param url
	 * @return
	 */
	public static String getUploadPath(String url){
		return url.replace(getUploadFileURL(),getUploadDirectory());
	}

	public static  String getVersion(){
		return  version;
	}

}
