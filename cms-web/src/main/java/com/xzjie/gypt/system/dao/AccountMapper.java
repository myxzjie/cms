package com.xzjie.gypt.system.dao;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.system.model.Account;

public interface AccountMapper extends BaseMapper<Account, Long>{
    int deleteByPrimaryKey(Long userId);

    /**
     * 查询用户是否已存在
     * @param record 对象参数 name,phone,eMail
     * @return 存在返回 >0, 不存在返回 <=0
     */
    int exist(Account record);

    Account selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    /**
     * 获得最大ID值
     * @return
     */
    long getIdMaxValue();
}