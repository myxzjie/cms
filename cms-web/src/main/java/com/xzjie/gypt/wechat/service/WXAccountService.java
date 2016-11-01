/**
 * radp-cms
 * @Title: WXAccountService.java 
 * @Package com.xzjie.gypt.wechat.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月2日
 */
package com.xzjie.gypt.wechat.service;

import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.wechat.model.WXAccount;

/**
 * @className WXAccountService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月2日 下午11:18:30 
 * @version V0.0.1 
 */
public interface WXAccountService extends BaseService<WXAccount, Long>{

	boolean isExist(Long userId);
	
	WXAccount getWxAccountByUserId(Long userId);
}
