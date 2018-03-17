package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.State;
import com.xzjie.local.ueditor.LocalBase64Uploader;
import com.xzjie.local.ueditor.LocalBinaryUploader;
import com.xzjie.oss.conf.Configuration;
import com.xzjie.oss.ueditor.OSSBase64Uploader;
import com.xzjie.oss.ueditor.OSSUploader;
import org.apache.commons.lang.StringUtils;

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

		if(Configuration.useStatus()){//使用阿里云oss 存储
			if ("true".equals(this.conf.get("isBase64"))) {
				state = OSSBase64Uploader.save(this.request.getParameter(filedName),this.conf);
			} else {
				state = OSSUploader.save(this.request, this.conf);
			}
		}else{
			/* date:2017-10-7 14:34:27
            *  xzjie
            *  修改保存本地的文件路径
            * */
			if ("true".equals(this.conf.get("isBase64"))) {
				//state = Base64Uploader.save(this.request.getParameter(filedName),this.conf);
				state = LocalBase64Uploader.save(this.request.getParameter(filedName),this.conf);
			} else {
				//state = BinaryUploader.save(this.request, this.conf);
				state = LocalBinaryUploader.save(this.request, this.conf);
			}
		}

		return state;
	}
}
