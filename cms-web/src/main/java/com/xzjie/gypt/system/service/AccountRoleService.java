/**
 * radp-cms
 * @Title: AccountRoleService.java 
 * @Package com.xzjie.gypt.system.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月27日
 */
package com.xzjie.gypt.system.service;

import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.system.model.AccountRole;

/**
 * @className AccountRoleService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月27日 下午11:32:20 
 * @version V0.0.1 
 */
public interface AccountRoleService extends BaseService<AccountRole, Long>{

	AccountRole getAccountRoleByUserId(Long userId);
	
	void updateAccountRole(AccountRole record);
}
