package com.xzjie.et.wechat.web.controller;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxMessageService;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzjie.et.core.web.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("${web.adminPath}/wx-message")
public class AdminWxMessageController extends BaseController {
	private final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	private WxMessageService wxMessageService;

	@RequestMapping(value = { "", "/", "index" })
	public String indexView() {
		return getRemoteView("wechat/wx_message/wx_message");
	}

	@RequestMapping("datapage")
	@ResponseBody
	public Map<String, Object> dataPage(WxMessage model, Page page) {
		PageEntity<WxMessage> pageEntity = new PageEntity<WxMessage>();

		model.setSiteId(getSiteId());
		pageEntity.setT(model);
		pageEntity.setPage(page);
		try {
			PageEntity<WxMessage> res = wxMessageService.getListPage(pageEntity);

			return MapResult.bootPage(res.getRows(), res.getPage());
		} catch (Exception e) {
			LOG.error("获得数据错误：{}", e);
		}
		return MapResult.mapError("601");
	}
}
