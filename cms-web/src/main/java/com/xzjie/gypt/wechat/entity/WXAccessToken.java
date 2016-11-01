/**
 * radp-cms
 * @Title: WXAccessToken.java 
 * @Package com.xzjie.gypt.wechat.model
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月4日
 */
package com.xzjie.gypt.wechat.entity;

import java.sql.Timestamp;

/**
 * @className WXAccessToken.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月4日 下午10:35:43 
 * @version V0.0.1 
 */
public class WXAccessToken {

	private String access_token;
	
	private Long expires_in;
	
	private Timestamp timestamp;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
