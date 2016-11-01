/**
 * radp-cms
 * @Title: WechatController.java 
 * @Package com.xzjie.gypt.wechat.web.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月2日
 */
package com.xzjie.gypt.wechat.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.system.web.controller.BaseController;
import com.xzjie.gypt.wechat.entity.WXAccessToken;
import com.xzjie.gypt.wechat.model.WXAccount;
import com.xzjie.gypt.wechat.service.WXAccountService;
import com.xzjie.gypt.wechat.service.impl.WechatHelper;

/**
 * @className WechatController.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年9月2日 下午10:41:57
 * @version V0.0.1
 */
@Controller
@RequestMapping(value = "wechat")
public class WechatController extends BaseController {

	@Autowired
	private WXAccountService wxAccountService;
	@Autowired
	private WechatHelper wechatHelper;

	@RequestMapping(value = "signature/{appid}", method = RequestMethod.GET)
	public void signature(@PathVariable Long appid,
			@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			@RequestParam(value = "nonce", required = true) String nonce,
			@RequestParam(value = "echostr", required = true) String echostr, HttpServletResponse response)
					throws IOException {
		PrintWriter writer = response.getWriter();
		WXAccount model = wxAccountService.get(appid);

		if (model == null) {
			writer.print("appid error");
			return;
		}

		String[] values = { model.getToken(), timestamp, nonce };

		Arrays.sort(values); // 字典序排序

		String value = values[0] + values[1] + values[2];

		String sign = DigestUtils.sha1Hex(value);
		
		logger.info(">>signature:"+signature);

		if (signature.equals(sign)) {// 验证成功返回ehcostr
			writer.print(echostr);
		} else {
			writer.print("error");
		}

		writer.flush();

		writer.close();

	}
	
	@RequestMapping(value="accesstoken")
	@ResponseBody
	public WXAccessToken getAccessToken(){
		WXAccount model = wxAccountService.get(getWXAppid());
		return wechatHelper.getAccessToken(model.getAppid(), model.getAppsecret(), request.getSession());
	}
}