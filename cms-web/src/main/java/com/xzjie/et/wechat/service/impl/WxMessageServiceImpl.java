package com.xzjie.et.wechat.service.impl;

import com.xzjie.et.system.model.Account;
import com.xzjie.et.wechat.entity.MessageData;
import com.xzjie.et.wechat.entity.WxAccessToken;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.et.wechat.service.WxAccountFollowService;
import com.xzjie.et.wechat.service.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.et.wechat.dao.WxMessageMapper;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxMessageService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;

import java.util.ArrayList;
import java.util.List;

@Service("wxMessageService")
public class WxMessageServiceImpl extends AbstractBaseService<WxMessage, Long> implements WxMessageService {

    @Autowired
    private WxMessageMapper wxMessageMapper;
    @Autowired
    private WechatHelper wechatHelper;
    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private WxAccountFollowService wxAccountFollowService;

    @Override
    protected BaseMapper<WxMessage, Long> getMapper() {
        return wxMessageMapper;
    }

    @Override
    public boolean send(Long siteId, Long groupId, Long messageId) {
        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(siteId);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);

        WxMessage message =get(messageId);
        List<WxAccountFollow> accountFollows = wxAccountFollowService.getAccountFollowByGroupId(groupId);
        List<String> touser =new ArrayList<>();
        for (WxAccountFollow accountFollow:accountFollows){
            touser.add(accountFollow.getOpenId());
        }

        MessageData messageData= MessageData.builder().add(message.getContent());
        messageData.setMsgtype("text");
        messageData.setTouser(touser);

        return wechatHelper.message(accessToken.getAccess_token(),messageData.build());
    }

    @Override
    public void batchSave(Long siteId, Long userId, List<WxMessage> messages) {
        for (WxMessage message :messages){
            message.setSiteId(siteId);
            message.setUserId(userId);
            this.save(message);
        }
//        wxMessageMapper.batchInsert(messages);
    }
}
