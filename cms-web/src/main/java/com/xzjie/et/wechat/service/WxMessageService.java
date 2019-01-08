package com.xzjie.et.wechat.service;

import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.mybatis.core.service.BaseService;
import com.xzjie.mybatis.page.PageEntity;

import java.util.List;

public interface WxMessageService extends BaseService<WxMessage, Long> {

    boolean send(Long siteId, Long groupId, Long messageId);

    void batchSave(Long siteId, Long userId, List<WxMessage> messages);

    PageEntity<WxMessage> getMessageListPage(PageEntity<WxMessage> pageEntity);

}
