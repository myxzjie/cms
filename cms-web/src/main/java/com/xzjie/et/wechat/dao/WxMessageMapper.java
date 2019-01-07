package com.xzjie.et.wechat.dao;

import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.mybatis.core.dao.BaseMapper;

import java.util.List;

public interface WxMessageMapper extends BaseMapper<WxMessage, Long> {
    int batchInsert(List<WxMessage> messages);
}