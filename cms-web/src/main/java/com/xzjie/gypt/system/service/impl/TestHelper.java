package com.xzjie.gypt.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.common.service.BaseService;
import com.xzjie.gypt.core.service.DefaultAbstractBaseService;
import com.xzjie.gypt.system.dao.AccountMapper;
import com.xzjie.gypt.system.model.Account;

@Service
public class TestHelper extends DefaultAbstractBaseService<Account,Long> implements BaseService<Account, Long>{

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	protected BaseMapper<Account, Long> getMapper() {
		return accountMapper;
	}

	

	
}
