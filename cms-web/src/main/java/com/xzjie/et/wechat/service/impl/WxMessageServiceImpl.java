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
import com.xzjie.mybatis.page.PageEntity;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
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

        WxMessage wxMessage = new WxMessage();
        wxMessage.setUserId(userId);
        wxMessage.setSiteId(siteId);
        wxMessage.setCreateDate(new Date());

        this.save(wxMessage);

        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(siteId);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);

        MateriaNewsData newsData = MateriaNewsData.builder();

        for (WxMessage message : messages) {
            message.setpId(wxMessage.getId());
            message.setSiteId(siteId);
            message.setUserId(userId);

            String path = WebUtils.getUploadPath(message.getThumbMedia());
            File file = new File(path);
            if (!file.exists()) {
                throw new RuntimeException("media file not exist");
            }
            String thumbMediaId = wechatHelper.addMateria(accessToken.getAccess_token(), MediaType.image.name(), file);
            message.setThumbMediaId(thumbMediaId);

            //
            if (StringUtils.isNotBlank(message.getMedia())) {
                String mediaPath = WebUtils.getUploadPath(message.getMedia());
                File mediaFile = new File(mediaPath);
                if (!mediaFile.exists()) {
                    throw new RuntimeException("media file not exist");
                }
                String mediaId = wechatHelper.addMateria(accessToken.getAccess_token(), message.getMsgtype(), mediaFile);
                message.setMediaId(mediaId);
            }

            String content = StringEscapeUtils.unescapeHtml4(message.getContent());
            newsData.add(MateriaNewsData.getItemMap()
                    .add("title", message.getTitle())
                    .add("thumb_media_id", message.getThumbMediaId())
                    .add("author", message.getAuthor())
                    .add("digest", message.getDigest())
                    .add("show_cover_pic", message.getShowCoverPic() + "")
                    .add("content", content)
                    .add("content_source_url", message.getContentSourceUrl())
                    .add("need_open_comment", message.getNeedOpenComment() + "")
                    .add("only_fans_can_comment", message.getOnlyFansCanComment() + ""));
            this.save(message);
        }

        String mediaId = wechatHelper.addMateriaNews(accessToken.getAccess_token(), newsData.build());
        wxMessage.setMediaId(mediaId);
        this.update(wxMessage);
//        wxMessageMapper.batchInsert(messages);
    }

    @Override
    public PageEntity<WxMessage> getMessageListPage(PageEntity<WxMessage> pageEntity) {
        List<WxMessage> list = wxMessageMapper.selectListPage(pageEntity);

        for (WxMessage message : list) {
            WxMessage model = new WxMessage();
            model.setpId(message.getId());
            model.setSiteId(message.getSiteId());
            List<WxMessage> messages = wxMessageMapper.selectList(model);

            message.setMessages(messages);
        }

        pageEntity.setRows(list);
        return pageEntity;
    }
}
