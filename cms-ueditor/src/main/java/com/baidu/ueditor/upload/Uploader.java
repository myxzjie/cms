package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.State;
import com.xzjie.oss.ueditor.OSSBase64Uploader;
import com.xzjie.oss.ueditor.OSSUploader;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Uploader {
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			//state = Base64Uploader.save(this.request.getParameter(filedName),this.conf);
			state = OSSBase64Uploader.save(this.request.getParameter(filedName),this.conf);
		} else {
			//state = BinaryUploader.save(this.request, this.conf);
			state = OSSUploader.save(this.request, this.conf);
		}

		return state;
	}
}
