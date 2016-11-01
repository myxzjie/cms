/**
 * radp-cms
 * @Title: WXButtonController.java 
 * @Package com.xzjie.gypt.wechat.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月5日
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
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.web.controller.BaseController;
import com.xzjie.gypt.wechat.entity.WXAccessToken;
import com.xzjie.gypt.wechat.model.WXAccount;
import com.xzjie.gypt.wechat.model.WXButton;
import com.xzjie.gypt.wechat.service.WXAccountService;
import com.xzjie.gypt.wechat.service.WXButtonService;
import com.xzjie.gypt.wechat.service.impl.WechatHelper;

/**
 * @className WXButtonController.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年9月5日 下午10:37:18
 * @version V0.0.1
 */
@Controller
@RequestMapping("wxbutton")
public class WXButtonController extends BaseController {

	@Autowired
	private WXAccountService wxAccountService;

	@Autowired
	private WXButtonService wxButtonService;

	@Autowired
	private WechatHelper wechatHelper;

	@RequestMapping(value = "index")
	public String indexView() {
		return "wechat/wxbutton/wxbutton_index";
	}

	@RequestMapping("edit")
	public String editView(Long id, Map<String, Object> modelMap) {
		if (id != null) {
			WXButton record = wxButtonService.get(id);
			if (record.getpButtonId() == 0) {
				record.setpButtonId(-1L);
			}
			modelMap.put("model", record);
		}
		return "wechat/wxbutton/wxbutton_edit";
	}

	@RequestMapping(value = "sync")
	@ResponseBody
	public Map<String, Object> syncButton() {
		try {
			WXAccount model = wxAccountService.get(this.getWXAppid());
			WXAccessToken accessToken = wechatHelper.getAccessToken(model.getAppid(), model.getAppsecret(),
					request.getSession());

			if (accessToken == null) {
				return MapResult.mapError(RspCode.R99998, "accessToken 错误");
			}
			wxButtonService.syncButton(getUserId(), accessToken.getAccess_token());
			return MapResult.mapOK(RspCode.R00000, "步菜单信息数据成功！");
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, "错误:" + e.getMessage());
		}

		// return "wechat/wxbutton/wxbutton_sync";
	}

	// @RequestMapping(value = "sync", method = RequestMethod.POST)
	//
	// public Map<String, Object> syncButton(ButtonEntity entity){
	//
	// return MapResult.mapError(RspCode.R99998,"");
	// }

	@RequestMapping(value = "datapage")
	@ResponseBody
	public Map<String, Object> dataPage(WXButton record, Page page) {
		PageEntity<WXButton> pageEntity = new PageEntity<WXButton>();

		record.setUserId(getUserId());

		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<WXButton> resources = wxButtonService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");

	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(WXButton model) {

		try {
			if (model.getpButtonId() == -1) {
				model.setpButtonId(0L);
			}
			model.setUserId(getUserId());

			wxButtonService.save(model);
			return MapResult.mapOK(RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}

	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(WXButton model) {
		try {
			if (model.getpButtonId() == -1) {
				model.setpButtonId(0L);
			}
			wxButtonService.update(model);
			return MapResult.mapOK(RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}

	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Long id) {
		if (wxButtonService.delete(id)) {
			return MapResult.mapOK(RspCode.R00000);
		}

		return MapResult.mapError(RspCode.R99997);
	}

	@RequestMapping(value = "tree", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resourceTree() {
		List<WXButton> buttons = wxButtonService.getWXButtonTree(0L, getUserId());

		WXButton button = new WXButton();
		button.setButtonId(-1L);
		button.setpButtonId(0L);
		button.setName("选择");

		buttons.add(button);
		return MapResult.mapOK(buttons);
	}
}
