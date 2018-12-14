package com.xzjie.et.wechat.dao;

import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.mybatis.core.dao.BaseMapper;

import java.util.List;

public interface WxAccountFollowMapper extends BaseMapper<WxAccountFollow, Long> {

    int exist(WxAccountFollow accountFollow);

    int updateByOpenId(WxAccountFollow accountFollow);

    List<WxAccountFollow> selectAccountFollowByGroupId(Long groupId);
}