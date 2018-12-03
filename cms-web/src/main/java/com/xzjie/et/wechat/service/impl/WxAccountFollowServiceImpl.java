package com.xzjie.et.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.et.wechat.dao.WxAccountFollowMapper;
import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.et.wechat.service.WxAccountFollowService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;

@Service("wxAccountFollowService")
public class WxAccountFollowServiceImpl extends AbstractBaseService<WxAccountFollow, Long> implements WxAccountFollowService {

	@Autowired
	private WxAccountFollowMapper wxAccountFollowMapper;
	
	@Override
	protected BaseMapper<WxAccountFollow, Long> getMapper() {
		return wxAccountFollowMapper;
	}

}
