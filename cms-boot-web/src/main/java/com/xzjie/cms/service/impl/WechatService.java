package com.xzjie.cms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzjie.cms.enums.KeyDataKey;
import com.xzjie.cms.model.KeyData;
import com.xzjie.cms.service.KeyDataService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class WechatService {

    @Autowired
    private KeyDataService keyDataService;

    public WxMpService create() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }


    private WxMpConfigStorage wxMpConfigStorage() {

        KeyData keyData = keyDataService.getKeyDataByKey(KeyDataKey.wechat_setting.name());

        JSONObject data = JSON.parseObject(keyData.getData());

        WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
        // 公众号appId
        configStorage.setAppId(data.getString("wechat_appid"));
        // 公众号appSecret
        configStorage.setSecret(data.getString("wechat_appsecret"));
        // 公众号Token
        configStorage.setToken(data.getString("wechat_token"));
        // 公众号EncodingAESKey
        configStorage.setAesKey(data.getString("wechat_encodingaeskey"));
        return configStorage;
    }
}
