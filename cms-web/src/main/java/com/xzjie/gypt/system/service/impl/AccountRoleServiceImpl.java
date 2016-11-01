/**
 * radp-cms
 * @Title: AccountRoleServiceImpl.java 
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月27日
 */
package com.xzjie.gypt.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.system.dao.AccountRoleMapper;
import com.xzjie.gypt.system.model.AccountRole;
import com.xzjie.gypt.system.service.AccountRoleService;

/**
 * @className AccountRoleServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月27日 下午11:32:43 
 * @version V0.0.1 
 */
@Service("accountRoleService")
public class AccountRoleServiceImpl implements AccountRoleService{
	
	@Autowired
	private AccountRoleMapper accountRoleMapper;
	
	@Override
	public boolean save(AccountRole record) {
		int row = accountRoleMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public void batchSave(List<AccountRole> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(AccountRole record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchUpdate(List<AccountRole> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(AccountRole record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<AccountRole> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccountRole get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountRole get(AccountRole record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountRole> getList(AccountRole record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountRole> getAllList(AccountRole record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountRole> getListPage(PageEntity<AccountRole> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountRole getAccountRoleByUserId(Long userId) {
		return accountRoleMapper.findAccountRoleByUserId(userId);
	}

	@Override
	public void updateAccountRole(AccountRole record) {
		
		accountRoleMapper.deleteByRoleByUserId(record.getUserId());
		
		this.save(record);
		
	}

}
