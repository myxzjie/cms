/**
 * radp-cms
 * @Title: AccountService.java 
 * @Package com.xzjie.gypt.system.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月18日
 */
package com.xzjie.gypt.system.service;

import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.system.entity.AccountEntity;
import com.xzjie.gypt.system.model.Account;

/**
 * @className AccountService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年6月18日 下午5:18:24 
 * @version V0.0.1 
 */
public interface AccountService extends BaseService<Account, Long>{

	Account getAccountLogin(String username, String stype);
	
	void save(AccountEntity parameter);
	
	void update(AccountEntity parameter);
	
	AccountEntity getAccount(Long userId);
	
	/**
	 * 修改密码
	 * @param userId
	 * @param newPass
	 * @return
	 * @throws Exception 
	 */
	boolean changePassword(Long userId, String password, String newPassword) throws Exception;
	
	/**
	 * 重置密码
	 * @param userId
	 * @return
	 */
	boolean resetPassword(Long userId);
}
