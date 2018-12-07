package com.xzjie.et.wechat.service.impl;

import java.sql.Timestamp;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xzjie.cache.ehcache.service.SystemCacheManager;
import com.xzjie.common.utils.HttpUtils;
import com.xzjie.core.utils.DateUtils;
import com.xzjie.et.core.utils.ConstantsUtils;
import com.xzjie.et.wechat.entity.WxAccessToken;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.model.WxAccountFollow;

@Service
public class WechatHelper {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${wechat.url}")
    private String wechatUrl;

    /**
     * 获得token
     *
     * @param wxAccount
     * @return
     */
    public WxAccessToken getAccessToken(WxAccount wxAccount) {
        String json = null;
        String key = ConstantsUtils.WECHAT_ACCESS_TOKEN + wxAccount.getSiteId();
        WxAccessToken wxAccessToken = null;
        try {

            wxAccessToken = SystemCacheManager.get(key, WxAccessToken.class);

        } catch (Exception e) {
            logger.error("get wechat token fail:" + json, e);

        } finally {
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

    /**
     * 创建菜单
     *
     * @param accessToken
     * @param jsonData
     * @return
     * @throws Exception
     */
    public boolean createButton(String accessToken, String jsonData) throws Exception {
        String url = wechatUrl + "cgi-bin/menu/create?access_token=" + accessToken;

        if (logger.isInfoEnabled()) {
            logger.info("\n create Button:" + jsonData);
        }

        String json = HttpUtils.doPost(url, jsonData);
        if (logger.isInfoEnabled()) {
            logger.info("\n 结果:" + json);
        }

        //{"errcode":0,"errmsg":"ok"}
        JSONObject jsonObject = JSONObject.parseObject(json);
//        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(json);

        if (jsonObject == null) {
            throw new Exception("同步菜单信息数据失败！同步自定义菜单URL地址不正确。");
        }


        if (0 != jsonObject.getIntValue("errcode")) {
            throw new Exception("同步菜单信息数据失败！错误码为：" + jsonObject.getIntValue("errcode") + "错误信息为：" + jsonObject.getString("errmsg"));
        }

        return true;
    }

    public Map<String, Object> getAccountFollowList(String accessToken, String nextOpenId) throws Exception {
        String url = wechatUrl + "cgi-bin/user/get?access_token=" + accessToken + "&next_openid=" + nextOpenId;
        logger.info("同步粉丝入参消息如下:" + url);
        String json = HttpUtils.doGet(url);
        JSONObject jsonObject = JSONObject.parseObject(json);
//        logger.info("同步粉丝返回消息如下:" + jsonObject.toString());
        if (jsonObject.containsKey("errcode")) {
            throw new Exception("获得用户错误。");
        }
        Map<String, Object> map = new HashMap<>();
        List<WxAccountFollow> accountFollows = new ArrayList<WxAccountFollow>();
        if (jsonObject.containsKey("data")) {
            map.put("next_openid", jsonObject.getString("next_openid"));
            if (jsonObject.getJSONObject("data").containsKey("openid")) {
                JSONArray openidArr = jsonObject.getJSONObject("data").getJSONArray("openid");
                int length = openidArr.size();
                for (int i = 0; i < length; i++) {
                    String openId = openidArr.getString(i);
                    WxAccountFollow accountFollow = getAccountFollow(accessToken, openId);
                    // 设置公众号
                    //fans.setAccount(WxMemoryCacheClient.getAccount());
                    accountFollows.add(accountFollow);
                }
                map.put("accountFollows", accountFollows);
            }
        }
        return map;
    }

    public WxAccountFollow getAccountFollow(String accessToken, String openId) throws Exception {


        String url = wechatUrl + "cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
        String json = HttpUtils.doGet(url);
        if (logger.isInfoEnabled()) {
            logger.info("\n 结果:" + json);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);

        if (null == jsonObject) {
            throw new Exception("获得用户错误。");
        }
        // logger.info("获取用户信息接口返回结果：" + jsonObject.toString());
        if (jsonObject.containsKey("errcode")) {
            throw new Exception(
                    "信息数据失败！错误码为：" + jsonObject.getIntValue("errcode") + "错误信息为：" + jsonObject.getString("errmsg"));
        }

        WxAccountFollow accountFollow = new WxAccountFollow();

        accountFollow.setOpenId(jsonObject.getString("openid"));// 用户的标识
        accountFollow.setSubscribe(new Integer(jsonObject.getIntValue("subscribe")));// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
        if (jsonObject.containsKey("subscribe_time")) {
            accountFollow.setSubscribeDate(
                    DateUtils.timestampToDate(jsonObject.getString("subscribe_time"), "yyyy-MM-dd HH:mm:ss"));// 用户关注时间
        }
        if (jsonObject.containsKey("nickname")) {// 昵称
            accountFollow.setNickName(jsonObject.getString("nickname"));
        }
        if (jsonObject.containsKey("sex")) {// 用户的性别（1是男性，2是女性，0是未知）
            accountFollow.setSex(jsonObject.getIntValue("sex"));
        }
        if (jsonObject.containsKey("language")) {// 用户的语言，简体中文为zh_CN
            accountFollow.setLanguage(jsonObject.getString("language"));
        }
        if (jsonObject.containsKey("country")) {// 用户所在国家
            accountFollow.setCountry(jsonObject.getString("country"));
        }
        if (jsonObject.containsKey("province")) {// 用户所在省份
            accountFollow.setProvince(jsonObject.getString("province"));
        }
        if (jsonObject.containsKey("city")) {// 用户所在城市
            accountFollow.setCity(jsonObject.getString("city"));
        }
        if (jsonObject.containsKey("headimgurl")) {// 用户头像
            accountFollow.setAvatar(jsonObject.getString("headimgurl"));
        }
        if (jsonObject.containsKey("remark")) {
            accountFollow.setRemark(jsonObject.getString("remark"));
        }
        accountFollow.setStatus(1);
        accountFollow.setCreateDate(new Date());
        return accountFollow;

    }

    public void getTemplate(String accessToken){
        String url = wechatUrl + "cgi-bin/template/api_add_template?access_token="+accessToken+"";

        String json = HttpUtils.doGet(url);
        if (logger.isInfoEnabled()) {
            logger.info("\n 结果:" + json);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
    }


    public void getTemplateList(String accessToken){
        String url = wechatUrl + "/cgi-bin/template/get_all_private_template?access_token="+accessToken;

        String json = HttpUtils.doGet(url);
        if (logger.isInfoEnabled()) {
            logger.info("\n 结果:" + json);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
    }

    public void setIndustry(String accessToken,Map<String,Object> params){
        String url = wechatUrl + "/cgi-bin/template/api_set_industry?access_token="+accessToken;

        String json = HttpUtils.doPost(url,params);
        if (logger.isInfoEnabled()) {
            logger.info("\n 结果:" + json);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
    }

    public void messageTemplateSend(String accessToken,String params){
        String url = wechatUrl + "/cgi-bin/message/template/send?access_token="+accessToken;
        String json = HttpUtils.doPost(url,params);
        if (logger.isInfoEnabled()) {
            logger.info("\n 结果:" + json);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
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
