package com.xzjie.gypt.system.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebUtils extends com.xzjie.gypt.common.web.WebUtils {
	private WebUtils() {
	}

	private static String webUrl;
	private static String uploadImageDirectory;
	private static String uploadImageWeb;
	private static String uploadImgageExts;
	private static Long uploadImgageMaxSize;
	private static String downloadFileDirectory;
	private static String downloadFileWeb;

	private static String uploadFileExts;

	private static Long uploadFileMaxSize;

	@Value(value = "${web.url}")
	public void setWebUrl(String webUrl) {
		WebUtils.webUrl = webUrl;
	}

	@Value(value = "${upload.image.directory}")
	public void setUploadImageDirectory(String uploadImageDirectory) {
		WebUtils.uploadImageDirectory = uploadImageDirectory;
	}

	@Value(value = "${upload.image.web}")
	public void setUploadImageWeb(String uploadImageWeb) {
		WebUtils.uploadImageWeb = uploadImageWeb;
	}

	@Value("${upload.imgage.exts}")
	public void setUploadImgageExts(String uploadImgageExts) {
		WebUtils.uploadImgageExts = uploadImgageExts;
	}

	@Value(value = "${upload.imgage.maxsize}")
	public void setUploadImgageMaxSize(Long uploadImgageMaxSize) {
		WebUtils.uploadImgageMaxSize = uploadImgageMaxSize;
	}

	@Value(value = "${download.file.directory}")
	public void setDownloadFileDirectory(String downloadFileDirectory) {
		WebUtils.downloadFileDirectory = downloadFileDirectory;
	}

	@Value(value = "${download.file.web}")
	public void setDownloadFileWeb(String downloadFileWeb) {
		WebUtils.downloadFileWeb = downloadFileWeb;
	}

	@Value("${uploadFileExts}")
	public void setUploadFileExts(String uploadFileExts) {
		WebUtils.uploadFileExts = uploadFileExts;
	}

	@Value(value = "${uploadFileMaxSize}")
	public void setUploadFileMaxSize(Long uploadFileMaxSize) {
		WebUtils.uploadFileMaxSize = uploadFileMaxSize;
	}

	public static String getWebUrl() {
		return webUrl;
	}

	public static String getUploadImageDirectory() {
		return uploadImageDirectory;
	}

	public static String getUploadImageWeb() {
		return uploadImageWeb;
	}

	public static String getUploadImgageExts() {
		return uploadImgageExts;
	}

	public static Long getUploadImgageMaxSize() {
		return uploadImgageMaxSize;
	}

	public static String getUploadFileExts() {
		return uploadFileExts;
	}

	public static String getDownloadFileDirectory() {
		return downloadFileDirectory;
	}

	public static String getDownloadFileWeb() {
		return downloadFileWeb;
	}

	public static Long getUploadFileMaxSize() {
		return uploadFileMaxSize;
	}

}
