/**
 * radp-cms
 * @Title: RoleController.java 
 * @Package com.xzjie.gypt.system.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月21日
 */
package com.xzjie.gypt.system.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.entity.RoleResourceEntity;
import com.xzjie.gypt.system.model.Role;
import com.xzjie.gypt.system.model.RoleResource;
import com.xzjie.gypt.system.service.RoleResourceService;
import com.xzjie.gypt.system.service.RoleService;

/**
 * @className RoleController.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月21日 下午10:37:45 
 * @version V0.0.1 
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleResourceService roleResourceService;
	
	@RequestMapping(value="")
	public String index(){
		return "role/role_index";
	}
	
	@RequestMapping(value="edit")
	public String editView(Long id,Map<String, Object> modelMap){
		if(id!=null){
			Role resource=roleService.get(id);
			modelMap.put("model", resource);
		}
		return "role/role_edit";
	}
	
	@RequestMapping(value="permission")
	private String permissionView(Long id,Map<String, Object> modelMap){
		
		modelMap.put("roleId", id);
		return "role/role_permission";
	}
	
	@RequestMapping(value="datapage", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDataPage(Role record,Page page){
		PageEntity<Role> pageEntity=new PageEntity<Role>();
		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Role> resources= roleService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
	}
	
	@RequestMapping(value="data", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getData(Role record){
		List<Role> list= roleService.getList(record);
		return MapResult.mapOK(list, "OK");
	}
	
	@RequestMapping(value="roleresourcedata", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRoleResourceData(Long id){
		List<RoleResource> list = roleResourceService.getRoleResourceByRoleId(id);
		return MapResult.mapOK(list, "OK");
	}
	
	
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Role model){
		model.setCreateUser(getPrincipal().getUserId());
		if(roleService.save(model)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99998);
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(Role model){
		if(roleService.update(model)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Long id){
		if(roleService.delete(id)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
	
	@RequestMapping(value="permission/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> permission(@PathVariable Long id,RoleResourceEntity entity){
		try{
			roleResourceService.save(entity.getRoleResources(), id);
			return MapResult.mapOK(RspCode.R00000);
		}catch(Exception e){
			return MapResult.mapError(RspCode.R99997);
		}
		
	}
}
