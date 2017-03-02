/**
 * radp-cms
 * @Title: ResourceController.java 
 * @Package com.xzjie.gypt.system.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月20日
 */
package com.xzjie.gypt.system.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.model.Resource;
import com.xzjie.gypt.system.service.ResourceService;

/**
 * @className ResourceController.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年6月20日 下午11:47:01 
 * @version V0.0.1 
 */
@Controller
@RequestMapping("resource")
public class ResourceController extends BaseController {
	
	@Autowired
	private ResourceService resourceService;

	@RequestMapping("")
	public String indexView(){
		return "resource/resource_index";
	}
	
	@RequestMapping("edit")
	public String editView(Long id,Map<String, Object> modelMap){
		if(id!=null){
			Resource resource=resourceService.get(id);
			modelMap.put("model", resource);
		}
		return "resource/resource_edit";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Resource model){
		model.setCreateUser(getPrincipal().getUserId());
		if(resourceService.save(model)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99998);
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(Resource model){
		if(resourceService.update(model)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Long id){
		if(resourceService.delete(id)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
	
	@RequestMapping(value="tree", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resourceTree(){
		List<Resource> resources= resourceService.getResourceTree(0L);
		return MapResult.mapOK(resources);
		//return MapResult.mapOK(resources.get(0).getChildren());
	}
	
	@RequestMapping(value="data")
	@ResponseBody
	public Map<String, Object> getData(Resource record,Page page){
		PageEntity<Resource> pageEntity=new PageEntity<Resource>();
		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Resource> resources= resourceService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
	}
	
	@RequestMapping(value="resourceuser", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getResourceUserData(){
		List<Resource> resources= resourceService.getResourceUser(getPrincipal().getUserId());
		return MapResult.mapOK(resources, "OK");
	}
	
	
	/*public String getData(Resource record,Page page){
		PageEntity<Resource> pageEntity=new PageEntity<Resource>();
		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Resource> resources= resourceService.getListPage(pageEntity);
		return FlexigridJson.getJson(page.getCurrentPage(), resources);
	}*/
	
}
