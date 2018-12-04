package com.xzjie.et.wechat.dao;

import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.mybatis.core.dao.BaseMapper;

public interface WxAccountFollowMapper extends BaseMapper<WxAccountFollow, Long> {

    int exist(WxAccountFollow accountFollow);
}