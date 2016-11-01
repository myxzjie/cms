/**
 * radp-cms
 * @Title: MaterialController.java 
 * @Package com.xzjie.gypt.system.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月5日
 */
package com.xzjie.gypt.system.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.model.Upload;
import com.xzjie.gypt.system.service.UploadService;

/**
 * @className MaterialController.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月5日 下午3:14:43 
 * @version V0.0.1 
 */
@Controller
@RequestMapping("material")
public class MaterialController {
	
	@Autowired
	private UploadService uploadService;

	@RequestMapping("index")
	public String indexView(){
		
		return "material/mater_index";
	}
	
	@RequestMapping("uploadfile")
	public String uploadFileView(){
		return "material/upload_file";
	}
	
	@RequestMapping(value="datapage")
	@ResponseBody
	public Map<String, Object> dataPage(Upload record,Page page){
		PageEntity<Upload> pageEntity=new PageEntity<Upload>();
		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Upload> resources= uploadService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
		
	}
}
