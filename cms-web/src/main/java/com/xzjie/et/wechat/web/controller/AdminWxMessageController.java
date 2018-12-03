package com.xzjie.et.wechat.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzjie.et.core.web.BaseController;

@Controller
@RequestMapping("${web.adminPath}/wx-message")
public class AdminWxMessageController extends BaseController {

	@RequestMapping(value = { "", "/", "index" })
	public String indexView() {
		return getRemoteView("wechat/wx_message/wx_message");
	}
}
