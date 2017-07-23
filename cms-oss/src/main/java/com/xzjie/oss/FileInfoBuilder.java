package com.xzjie.oss;

import java.util.Random;

public class FileInfoBuilder {

	private String dir;

	private String suffix;
	
	private String prefix="cms/";

	public FileInfoBuilder() {

	}

	public FileInfoBuilder(String dir,String suffix) {
		this();
		this.dir=dir;
		this.suffix=suffix;
	}
	
	
	public String getPath(){
		String path=prefix;
		String folder=null;
		if (".jpg,.jpeg,.gif,.png".contains(getSuffix())) {// 图片
			folder = "image";
		} else if (".avi,.rmvb,.rm,.asf,.divx,.mpg,.mpeg,.mpe,.wmv,.mp4,.mkv,.vob".contains(getSuffix())) {// 视频
			folder = "video";
		} else if (".wma,.mp3,.wav,.mid".contains(getSuffix())) {// 音频
			folder = "audio";
		} else if (".txt,.doc,.docx,.xls,.xlsx,.ppt,.pptx".contains(getSuffix())) {// 文档
			folder = "document";
		} else if(".rar,.zip,.7z,.Z,.bz2,.tar,.tar.gz,.gz".contains(getSuffix())) {//压缩包
			folder = "package";
		} else {// 其他（未分类）
			folder = "other";
		}
		
		path+=createPath(folder);
		return path;
	}
	
	private String createPath(String folder) {
		String path = folder + "/";
		if (!dir.isEmpty()) {
			path += dir + (dir.endsWith("/") ? "" : "/");
		}
		if (!suffix.isEmpty()) {
			path += (suffix.startsWith(".") ? suffix.substring(1) : suffix) + "/";
		}

		path += fileName();

		return path;
	}

	private String fileName() {
		String fileName = System.currentTimeMillis() + "_" + getRandom(6) + suffix;
		return fileName;
	}
	
	private String getRandom(int i) {
		Random jjj = new Random();
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
