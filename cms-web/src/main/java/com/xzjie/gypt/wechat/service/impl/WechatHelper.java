/**
 * radp-cms
 * @Title: WechatHelper.java 
 * @Package com.xzjie.gypt.wechat.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月3日
 */
package com.xzjie.gypt.wechat.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzjie.gypt.common.utils.DateUtils;
import com.xzjie.gypt.common.utils.HttpUtils;
import com.xzjie.gypt.common.utils.JSONHelper;
import com.xzjie.gypt.common.utils.constants.ConstantsUtils;
import com.xzjie.gypt.wechat.entity.WXAccessToken;

/**
 * @className WechatHelper.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年9月3日 下午6:20:53
 * @version V0.0.1
 */
@Service
public class WechatHelper {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${wechat.url}")
	private String wechatUrl;
	
	
	public boolean createButton(String accessToken,String jsonData) throws Exception{
		String url = wechatUrl + "cgi-bin/menu/create?access_token="+accessToken;
		
		if(logger.isInfoEnabled()){
			logger.info("\n create Button:"+jsonData);
		}
		
		String json = HttpUtils.doPost(url, jsonData);
		//{"errcode":0,"errmsg":"ok"}
		net.sf.json.JSONObject jsonObject =net.sf.json.JSONObject.fromObject(json);
		
		if(logger.isInfoEnabled()){
			logger.info("\n 结果:"+json);
		}
		
		if(jsonObject==null){
			throw new Exception("同步菜单信息数据失败！同步自定义菜单URL地址不正确。");
		}
		
		
		if (0 != jsonObject.getInt("errcode")) {
			throw new Exception("同步菜单信息数据失败！错误码为："+jsonObject.getInt("errcode")+"错误信息为："+jsonObject.getString("errmsg"));
		}
		
		return true;
	}

	public WXAccessToken getAccessToken(String appid, String appsecret, HttpSession session) {
		String json = null;
		
		try {
			Object obj = session.getAttribute(ConstantsUtils.SESSION_WECHAT_ACCESS_TOKEN);
			if (obj == null || !expires((WXAccessToken) obj)) {
				String url = wechatUrl + "cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret="
						+ appsecret;
				Date formatDate = new Date();
				json = HttpUtils.doGet(url);

				WXAccessToken accessToken = JSONHelper.getObject(json, WXAccessToken.class);

				accessToken.setTimestamp(new Timestamp(formatDate.getTime()+accessToken.getExpires_in()));
				//写入session
				session.setAttribute(ConstantsUtils.SESSION_WECHAT_ACCESS_TOKEN, accessToken);
				//从新获取session
				obj = session.getAttribute(ConstantsUtils.SESSION_WECHAT_ACCESS_TOKEN);
			}
			return (WXAccessToken) obj;
		} catch (IOException e) {
			logger.error("get wechat token fail:" + json);
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @param accessToken
	 * @return
	 */
	private boolean expires(WXAccessToken accessToken) {
		Date formatDate = new Date();
		Long timeStamp = formatDate.getTime() - accessToken.getTimestamp().getTime();
		Long expires_in = accessToken.getExpires_in() * 1000;

		if (timeStamp > expires_in) {
			return false;
		}
		return true;

	}

}
