package com.xzjie.gypt.system.dao;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.system.model.Account;

public interface AccountMapper extends BaseMapper<Account, Long>{
    int deleteByPrimaryKey(Long userId);


    Account selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}