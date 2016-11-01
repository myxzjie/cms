/**
 * radp-cms
 * @Title: OrgController.java 
 * @Package com.xzjie.gypt.system.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月22日
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
import com.xzjie.gypt.system.model.Org;
import com.xzjie.gypt.system.service.OrgService;

/**
 * @className OrgController.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年7月22日 下午10:58:31
 * @version V0.0.1
 */
@Controller
@RequestMapping("org")
public class OrgController extends BaseController {

	@Autowired
	private OrgService orgService;

	@RequestMapping(value = "")
	public String index() {
		return "org/org_index";
	}

	@RequestMapping(value = "edit")
	public String editView(Long id, Map<String, Object> modelMap) {
		if (id != null) {
			Org org = orgService.get(id);
			
			modelMap.put("model", org);
		}
		return "org/org_edit";
	}

	@RequestMapping(value = "data")
	@ResponseBody
	public Map<String, Object> getOrg(Org record) {
		List<Org> list = orgService.getList(record);
		return MapResult.mapOK(list, "OK");
	}

	@RequestMapping(value = "datapage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDataPage(Org record, Page page) {
		PageEntity<Org> pageEntity = new PageEntity<Org>();
		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Org> org = orgService.getListPage(pageEntity);
		return MapResult.mapOK(org, pageEntity.getPage(), "OK");
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Org model) {
		model.setCreateUser(getPrincipal().getUserId());
		if (orgService.save(model)) {
			return MapResult.mapOK(RspCode.R00000);
		}

		return MapResult.mapError(RspCode.R99998);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(Org model) {
		if (orgService.update(model)) {
			return MapResult.mapOK(RspCode.R00000);
		}

		return MapResult.mapError(RspCode.R99997);
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Long id){
		if(orgService.delete(id)){
			return MapResult.mapOK(RspCode.R00000);
		}
		
		return MapResult.mapError(RspCode.R99997);
	}
}
