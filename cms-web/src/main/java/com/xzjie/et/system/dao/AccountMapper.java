package com.xzjie.et.system.dao;

import com.xzjie.et.system.model.Account;
import com.xzjie.mybatis.core.dao.BaseMapper;

public interface AccountMapper extends BaseMapper<Account, Long> {

	int exist(Account t);
	
	Account selectAccount(Account t);

	int resetPassword(Account t);
}