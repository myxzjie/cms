/**
 * radp-cms
 * @Title: WXAccountController.java 
 * @Package com.xzjie.gypt.wechat.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月3日
 */
package com.xzjie.gypt.wechat.web.controller;

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
import com.xzjie.gypt.common.utils.constants.RoleEnum;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.security.SystemAuthorizingRealm.Principal;
import com.xzjie.gypt.system.web.controller.BaseController;
import com.xzjie.gypt.wechat.model.WXAccount;
import com.xzjie.gypt.wechat.service.WXAccountService;

/**
 * @className WXAccountController.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月3日 上午11:05:21 
 * @version V0.0.1 
 */
@Controller
@RequestMapping("wxaccount")
public class WXAccountController extends BaseController{

	@Autowired
	private WXAccountService wxAccountService;

	@RequestMapping(value="index")
	private String indexView(){
		return "wechat/wxaccount/wechat_index";
	}
	
	@RequestMapping("edit")
	public String editView(Long id,Map<String, Object> modelMap){
		if(id!=null){
			WXAccount record=wxAccountService.get(id);
			modelMap.put("model", record);
			modelMap.put("roleId", record);
		}
		return "wechat/wxaccount/wechat_edit";
	}
	
	@RequestMapping(value="datapage")
	@ResponseBody
	public Map<String, Object> dataPage(WXAccount record,Page page){
		PageEntity<WXAccount> pageEntity=new PageEntity<WXAccount>();
		
		//if(RoleEnum.wechatadmin.toString().equals(getRoleCode())){
		record.setUserId(getUserId());
		//}
		
		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<WXAccount> resources= wxAccountService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
		
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(WXAccount model){
		Principal principal=getPrincipal();
		
		if(wxAccountService.isExist(principal.getUserId())){
			return MapResult.mapError(RspCode.R99998,"该用户已经存在微信公众号");
		}
		
		model.setUserId(principal.getUserId());
		model.setOrgId(principal.getOrgId());
		
		try{
			wxAccountService.save(model);
			return MapResult.mapOK(RspCode.R00000);
		}catch(Exception e){
			return MapResult.mapError(RspCode.R99998,e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(WXAccount model) {
		try {
			wxAccountService.update(model);
			return MapResult.mapOK(RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}

	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Long id){
		if(wxAccountService.delete(id)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
	
	
}
