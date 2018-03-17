package com.xzjie.et.system.dao;

import com.xzjie.et.system.model.AccountRole;
import com.xzjie.mybatis.core.dao.BaseMapper;

public interface AccountRoleMapper extends BaseMapper<AccountRole,Long>{

    AccountRole selectAccountRoleByUserId(Long userId);

    int updateByUserId(AccountRole t);
}