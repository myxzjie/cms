/**
 * radp-cms
 * @Title: WXButtonService.java 
 * @Package com.xzjie.gypt.wechat.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月5日
 */
package com.xzjie.gypt.wechat.service;

import java.util.List;

import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.wechat.model.WXButton;

/**
 * @className WXButtonService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月5日 下午10:24:26 
 * @version V0.0.1 
 */
public interface WXButtonService extends BaseService<WXButton, Long>{

	List<WXButton> getWXButtonTree(Long buttonId, Long userId);
	
	void syncButton(Long userId, String accessToken) throws Exception;
}
