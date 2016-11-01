package com.xzjie.gypt.wechat.dao;

import java.util.List;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.wechat.model.WXButton;

public interface WXButtonMapper extends BaseMapper<WXButton, Long>{
	
	List<WXButton> findWXButtonTree(WXButton record);
}