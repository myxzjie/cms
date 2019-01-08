package com.xzjie.et.wechat.service.impl;

import com.xzjie.et.core.web.controller.WebUtils;
import com.xzjie.et.system.model.Account;
import com.xzjie.et.wechat.entity.MateriaNewsData;
import com.xzjie.et.wechat.entity.MessageData;
import com.xzjie.et.wechat.entity.WxAccessToken;
import com.xzjie.et.wechat.enums.MediaType;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.et.wechat.service.WxAccountFollowService;
import com.xzjie.et.wechat.service.WxAccountService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.et.wechat.dao.WxMessageMapper;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxMessageService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;

import java.io.File;
import java.util.*;

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

        WxMessage message = get(messageId);
        List<WxAccountFollow> accountFollows = wxAccountFollowService.getAccountFollowByGroupId(groupId);
        List<String> touser = new ArrayList<>();
        for (WxAccountFollow accountFollow : accountFollows) {
            touser.add(accountFollow.getOpenId());
        }

        MessageData messageData = MessageData.builder().add(message.getContent());
        messageData.setMsgtype(message.getMsgtype());
        messageData.setTouser(touser);

        return wechatHelper.message(accessToken.getAccess_token(), messageData.build());
    }

    @Override
    public void batchSave(Long siteId, Long userId, List<WxMessage> messages) {
        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(siteId);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);

        MateriaNewsData newsData = MateriaNewsData.builder();

        for (WxMessage message : messages) {
            message.setSiteId(siteId);
            message.setUserId(userId);

            if ("news".equals(message.getMsgtype())) {
                String path = WebUtils.getUploadPath(message.getMedia());
                File file = new File(path);
                if (!file.exists()) {
                    throw new RuntimeException("media file not exist");
                }
                String mediaId = wechatHelper.addMateria(accessToken.getAccess_token(), MediaType.image.name(), file);
                message.setThumbMediaId(mediaId);
            }
            newsData.add(MateriaNewsData.itemMap()
                    .add("title", message.getTitle())
                    .add("thumb_media_id",message.getThumbMediaId())
                    .add("author",message.getAuthor())
                    .add("digest",message.getDigest())
                    .add("show_cover_pic",message.getShowCoverPic()+"")
                    .add("content", StringEscapeUtils.unescapeHtml4(message.getContent()))
                    .add("content_source_url",message.getContentSourceUrl())
                    .add("need_open_comment",message.getNeedOpenComment()+"")
                    .add("only_fans_can_comment",message.getOnlyFansCanComment()+""));
            this.save(message);
        }

        String mediaId =  wechatHelper.addMateriaNews(accessToken.getAccess_token(),newsData.build());

        WxMessage wxMessage =new WxMessage();

        wxMessage.setUserId(userId);
        wxMessage.setSiteId(siteId);
        wxMessage.setMediaId(mediaId);
        wxMessage.setCreateDate(new Date());

        this.save(wxMessage);

//        wxMessageMapper.batchInsert(messages);
    }
}
