/**
 * radp-cms
 * @Title: AccountServiceImpl.java 
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月18日
 */
package com.xzjie.gypt.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.RegexUtils;
import com.xzjie.gypt.common.utils.constants.ConstantsUtils;
import com.xzjie.gypt.system.dao.AccountMapper;
import com.xzjie.gypt.system.entity.AccountEntity;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.model.AccountRole;
import com.xzjie.gypt.system.service.AccountRoleService;
import com.xzjie.gypt.system.service.AccountService;

/**
 * @className AccountServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年6月18日 下午5:30:06 
 * @version V0.0.1 
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Autowired
	private AccountRoleService accountRoleService;

	
	
	@Override
	public Account getAccountLogin(String username,String stype) {
		Account record=new Account();
		record.setStype(stype);
		
		//用户名查询
		if(RegexUtils.checkUserName(username)){
			record.setName(username); 
		}else if (RegexUtils.checkMobile(username)) { //手机号查询
			record.setPhone(username);
		}else if(RegexUtils.checkEmail(username)){ //邮箱查询
			record.seteMail(username);
		}
		
		return accountMapper.getEntity(record);
	}
	
	@Override
	public void save(AccountEntity parameter) {
		Account model=parameter.getModel();
		AccountRole arModel=new AccountRole();
		
		this.save(model);
		
		arModel.setUserId(model.getUserId());
		arModel.setRoleId(parameter.getRoleId());
		
		accountRoleService.save(arModel);
	}

	@Override
	public boolean save(Account record) {
		
		record.setCreateDate(new Date());
		record.setLocked(Integer.parseInt(ConstantsUtils.ACCOUNT_NOT_LOCKED));
		record.setState(1); //状态 1正常，0失败
		passwordHelper.encryptPassword(record);
		int row=accountMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public void batchSave(List<Account> records) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(AccountEntity parameter){
		AccountRole accountRole=new AccountRole();
		
		Account model=parameter.getModel();
		
		accountRole.setUserId(model.getUserId());
		accountRole.setRoleId(parameter.getRoleId());
		
		accountRoleService.updateAccountRole(accountRole);		
		this.update(model);
	}

	@Override
	public boolean update(Account record) {
		int row=accountMapper.update(record);
		return row>0?true:false;
	}

	@Override
	public void batchUpdate(List<Account> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		Account record=new Account();
		record.setUserId(id);
		record.setState(0); //状态 1正常，0失败
		return this.update(record);
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Account record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<Account> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account get(Long id) {
		return accountMapper.getById(id);
	}

	@Override
	public Account get(Account record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getList(Account record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllList(Account record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getListPage(PageEntity<Account> record) {
		return accountMapper.findListPage(record);
	}

	@Override
	public AccountEntity getAccount(Long userId) {
		AccountEntity entity=new AccountEntity();
		
		Account account =this.get(userId);
		AccountRole accountRole = accountRoleService.getAccountRoleByUserId(userId);
		
		entity.setModel(account);
		if(accountRole!=null){
		entity.setRoleId(accountRole.getRoleId());
		}
		return entity;
	}

	

}
