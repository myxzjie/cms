package com.xzjie.gypt.wechat.dao;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.wechat.model.WXAccount;

public interface WXAccountMapper extends BaseMapper<WXAccount, Long>{
	
	int exist(WXAccount record);
	WXAccount findWxAccountByUserId(Long userId);
    /*int deleteByPrimaryKey(Long wxAccId);

    int insert(WXAccount record);

    int insertSelective(WXAccount record);

    

    int updateByPrimaryKeySelective(WXAccount record);

    int updateByPrimaryKey(WXAccount record);*/
}