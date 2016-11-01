/**
 * radp-cms
 * @Title: DownloadController.java 
 * @Package com.xzjie.gypt.system.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月5日
 */
package com.xzjie.gypt.system.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzjie.gypt.system.model.Upload;
import com.xzjie.gypt.system.service.UploadService;

/**
 * @className DownloadController.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年9月5日 下午4:30:19
 * @version V0.0.1
 */
@Controller
@RequestMapping("download")
public class DownloadController {
	
	@Autowired
	private UploadService uploadService;

	@RequestMapping(value="file")
	public void downloadFile(Long id,String fileName,HttpServletResponse response) throws IOException {
		
		File file =null;
		
		OutputStream out = null;
		try {
			Upload upload=uploadService.get(id);
			file=new File(upload.getUriPath());
			
			if (file == null || !file.exists()) {
				PrintWriter writer = response.getWriter();
				writer.print("download error");
				return;
			}
			//java.net.URLEncoder.encode("dd", "UTF-8");
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			out = response.getOutputStream();
			out.write(FileUtils.readFileToByteArray(file));
			out.flush();
		} catch (IOException e) {
			PrintWriter writer = response.getWriter();
			writer.print("download error");
			e.printStackTrace();
		}catch (Exception e) {
			PrintWriter writer = response.getWriter();
			writer.print("download error, not exists file.");
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
