package com.xzjie.et.wechat.service;

import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.mybatis.core.service.BaseService;

import java.util.List;

public interface WxAccountFollowService extends BaseService<WxAccountFollow, Long> {

    void batchSyncAccountFollow(Long siteId, String nextOpenId);

    List<WxAccountFollow> getAccountFollowByGroupId(Long groupId);
}
