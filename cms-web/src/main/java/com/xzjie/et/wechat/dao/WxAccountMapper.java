package com.xzjie.et.wechat.dao;


import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.mybatis.core.dao.BaseMapper;

public interface WxAccountMapper extends BaseMapper<WxAccount, Long> {

	int exist(WxAccount t);

	WxAccount selectWxAccount(WxAccount t);

    /*int deleteByPrimaryKey(Long wxAccId);

    int insert(WXAccount record);

    int insertSelective(WXAccount record);



    int updateByPrimaryKeySelective(WXAccount record);

    int updateByPrimaryKey(WXAccount record);*/
}