package com.xzjie.gypt.system.dao;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.system.model.AccountRole;

public interface AccountRoleMapper extends BaseMapper<AccountRole, Long>{
	 AccountRole findAccountRoleByUserId(Long userId);
	 
	 int deleteByRoleByUserId(Long userId);
   /* 

    int insert(AccountRole record);

    int insertSelective(AccountRole record);

   

    int updateByPrimaryKeySelective(AccountRole record);

    int updateByPrimaryKey(AccountRole record);*/
}