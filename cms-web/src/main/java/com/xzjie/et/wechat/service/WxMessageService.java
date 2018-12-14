package com.xzjie.et.wechat.service;

import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.mybatis.core.service.BaseService;

public interface WxMessageService extends BaseService<WxMessage, Long> {

    void send(Long siteId, Long groupId, Long messageId) throws Exception;
}
