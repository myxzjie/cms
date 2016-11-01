/**
 * radp-cms
 * @Title: AccountParameter.java 
 * @Package com.xzjie.gypt.system.model
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月27日
 */
package com.xzjie.gypt.system.entity;

import com.xzjie.gypt.system.model.Account;

/**
 * @className AccountParameter.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月27日 下午11:16:01 
 * @version V0.0.1 
 */
public class AccountEntity {

	private Account model;
	
	private Long roleId;

	public Account getModel() {
		return model;
	}

	public void setModel(Account model) {
		this.model = model;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
