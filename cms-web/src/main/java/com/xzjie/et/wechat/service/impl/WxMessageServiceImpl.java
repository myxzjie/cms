package com.xzjie.et.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.et.wechat.dao.WxMessageMapper;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxMessageService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;

@Service("wxMessageService")
public class WxMessageServiceImpl extends AbstractBaseService<WxMessage, Long> implements WxMessageService {
	
	@Autowired
	private WxMessageMapper wxMessageMapper;

	@Override
	protected BaseMapper<WxMessage, Long> getMapper() {
		return wxMessageMapper;
	}

}
