package com.xzjie.et.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.xzjie.cache.ehcache.service.SystemCacheManager;
import com.xzjie.common.utils.HttpUtils;
import com.xzjie.et.core.utils.ConstantsUtils;
import com.xzjie.et.wechat.entity.WxAccessToken;
import com.xzjie.et.wechat.model.WxAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class WechatHelper {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${wechat.url}")
    private String wechatUrl;

    public WxAccessToken getAccessToken(WxAccount wxAccount) {
        String json = null;
        String key = ConstantsUtils.WECHAT_ACCESS_TOKEN + wxAccount.getSiteId();
        WxAccessToken wxAccessToken =null;
        try {

            wxAccessToken = (WxAccessToken) SystemCacheManager.get(key);

        } catch (Exception e) {
            logger.error("get wechat token fail:" + json);
            e.printStackTrace();
        }finally {
            if (wxAccessToken == null || !expires(wxAccessToken)) {
                String url = wechatUrl + "cgi-bin/token?grant_type=client_credential&appid=" + wxAccount.getAppid() + "&secret="
                        + wxAccount.getAppsecret();
                Date formatDate = new Date();
                json = HttpUtils.doGet(url);

                wxAccessToken = JSON.parseObject(json, WxAccessToken.class);

                wxAccessToken.setTimestamp(new Timestamp(formatDate.getTime() + wxAccessToken.getExpires_in()));

                SystemCacheManager.put(key, wxAccessToken);
            }

        }
        return wxAccessToken;

    }

    public boolean createButton(String accessToken, String jsonData) throws Exception {
        String url = wechatUrl + "cgi-bin/menu/create?access_token=" + accessToken;

        if (logger.isInfoEnabled()) {
            logger.info("\n create Button:" + jsonData);
        }

        String json = HttpUtils.doPost(url, jsonData);
        //{"errcode":0,"errmsg":"ok"}
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(json);

        if (logger.isInfoEnabled()) {
            logger.info("\n 结果:" + json);
        }

        if (jsonObject == null) {
            throw new Exception("同步菜单信息数据失败！同步自定义菜单URL地址不正确。");
        }


        if (0 != jsonObject.getInt("errcode")) {
            throw new Exception("同步菜单信息数据失败！错误码为：" + jsonObject.getInt("errcode") + "错误信息为：" + jsonObject.getString("errmsg"));
        }

        return true;
    }

    /**
     * @param accessToken
     * @return
     */
    private boolean expires(WxAccessToken accessToken) {
        Date formatDate = new Date();
        Long timeStamp = formatDate.getTime() - accessToken.getTimestamp().getTime();
        Long expires_in = accessToken.getExpires_in() * 1000;

        if (timeStamp > expires_in) {
            return false;
        }
        return true;

    }
}
