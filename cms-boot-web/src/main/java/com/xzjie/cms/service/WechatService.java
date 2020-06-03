package com.xzjie.cms.service;


import com.xzjie.cms.dto.WxMediaUploadResult;
import com.xzjie.cms.dto.WxMessageResult;
import com.xzjie.cms.dto.WxOpenIdResult;
import com.xzjie.cms.dto.WxUserResult;
import com.xzjie.cms.enums.MediaFileType;

public interface WechatService {

    /**
     * 微信验证token
     *
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    boolean checkSignature(String timestamp, String nonce, String signature);

    boolean createButton(String json);

    boolean deleteButton();

    WxMediaUploadResult addMedia(String image);

    /**
     * 上传材料资源内容
     *
     * @param json
     * @return
     */
    WxMediaUploadResult addMediaArticle(String json);

    WxMediaUploadResult uploadMedia(String image, MediaFileType type);

    /**
     * 获得微信用户信息
     *
     * @param openId
     * @param lang
     * @return
     */
    WxUserResult getUser(String openId, String lang);

    /**
     * 获得微信粉丝列表
     *
     * @param nextOpenId
     * @return
     */
    WxOpenIdResult getOpenIds(String nextOpenId);

    WxMessageResult messagePreview(String json);
}
