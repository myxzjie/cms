package com.xzjie.et.wechat.dao;

import com.xzjie.et.wechat.model.WxGroupFollow;
import com.xzjie.mybatis.core.dao.BaseMapper;

import java.util.List;

public interface WxGroupFollowMapper extends BaseMapper<WxGroupFollow,Long> {
    int exist(WxGroupFollow model);

    int batchInsert(List<WxGroupFollow> list);


}