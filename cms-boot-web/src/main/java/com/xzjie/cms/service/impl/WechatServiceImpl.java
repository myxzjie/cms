package com.xzjie.cms.service.impl;

import com.xzjie.cms.core.utils.HttpUtils;
import com.xzjie.cms.dto.*;
import com.xzjie.cms.enums.KeyDataKey;
import com.xzjie.cms.enums.MediaFileType;
import com.xzjie.cms.exception.WxMpException;
import com.xzjie.cms.model.KeyData;
import com.xzjie.cms.service.KeyDataService;
import com.xzjie.cms.service.WechatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WechatServiceImpl implements WechatService {
    private static final String accessTokenKey = "wechat:access_token";

    @Autowired
    private KeyDataService keyDataService;
    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 通过检验signature
     *
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param signature 加密签名
     * @return
     */
    public boolean checkSignature(String timestamp, String nonce, String signature) {
        WxConfigStorage configStorage = this.getConfigStorage();
        String[] values = {configStorage.getToken(), timestamp, nonce};
        Arrays.sort(values); // 字典序排序
        String value = values[0] + values[1] + values[2];
        String sign = DigestUtils.sha1Hex(value);

        return signature.equals(sign);
    }

    private WxConfigStorage getConfigStorage() {
        KeyData keyData = keyDataService.getKeyDataByKey(KeyDataKey.wechat_setting.name());
        return WxConfigStorage.fromJson(keyData.getData());
    }

    public void expireAccessToken() {
        redisTemplate.expire(accessTokenKey, 0, TimeUnit.SECONDS);
    }

    public boolean isAccessTokenExpired() {
        // TTL key 的剩余生存时间,key不存在返回 null
        return redisTemplate.getExpire(accessTokenKey) < 2L;
    }

    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        redisTemplate.opsForValue().set(accessTokenKey, accessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
//        jedis.setex(this.accessTokenKey, expiresInSeconds - 200, accessToken);
    }

    /**
     * 获得token
     *
     * @param forceRefresh
     * @return
     */
    public String getAccessToken(boolean forceRefresh) {
        if (!this.isAccessTokenExpired() && !forceRefresh) {
            return this.getAccessToken();
        } else {
            WxConfigStorage configStorage = this.getConfigStorage();

            String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", configStorage.getAppId(), configStorage.getSecret());

            String result = HttpUtils.doGet(url);
            log.info("获得微信AccessToken:" + result);
            WxAccessToken accessToken = WxAccessToken.fromJson(result);
            this.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());

            return accessToken.getAccessToken();
        }
    }

    /**
     * 获得token
     *
     * @return
     */
    private String getAccessToken() {
        return redisTemplate.opsForValue().get(accessTokenKey);
    }

    public WxMediaUploadResult addMedia(String image) {
        File file = new File(getFilePath());
        HttpUtils.doDownload(image, file);
        WxMediaUploadResult mediaUploadResult = this.addMedia(MediaFileType.image, file);

        return mediaUploadResult;
    }

    private String getFilePath() {
//        LocalDateTime localDate = LocalDateTime.now();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //4.把 2019-01-01  转成  2019/01/01
//        String format = localDate.format(dtf);
        String tmpdir = System.getProperty("java.io.tmpdir");
        return tmpdir + "wechat/" + System.currentTimeMillis() +
                RandomUtils.nextInt(4) + ".png";
    }

    /**
     * 创建菜单
     *
     * @param json
     * @return
     */
    public boolean createButton(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doPost(url, json);
        log.info("创建微信菜单按钮:" + result);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }
        return true;
    }

    public boolean deleteButton() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doGet(url);
        log.info("删除微信菜单按钮:" + result);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }
        return true;
    }

    public WxMediaUploadResult addMedia(MediaFileType type, File file) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s", this.getAccessToken(false), type.name());

        String result = HttpUtils.doPostFile(url, file, "media");

        // 删除临时文件
        file.delete();

        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }
        return WxMediaUploadResult.fromJson(result);
    }

    /**
     * 公众号可以新增临时素材
     * 媒体文件在微信后台保存时间为3天，即3天后media_id失效。
     *
     * @param type
     * @return
     */
    public WxMediaUploadResult uploadMedia(String image, MediaFileType type) {

        File file = new File(getFilePath());
        HttpUtils.doDownload(image, file);

        String url = String.format("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s", getAccessToken(false), type.name());
        String result = HttpUtils.doPostFile(url, file, "media");

        // 删除临时文件
        file.delete();

        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }

        return WxMediaUploadResult.fromJson(result);
    }

    public WxMediaUploadResult addMediaArticle(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doPost(url, json);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }
        return WxMediaUploadResult.fromJson(result);
    }

    public WxMessageResult messagePreview(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doPost(url, json);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }
        return WxMessageResult.fromJson(result);
    }

    public WxMessageResult message(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doPost(url, json);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }

        return WxMessageResult.fromJson(result);
    }

    public boolean customMessage(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doPost(url, json);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }
        return true;
    }

    public WxUserResult getUser(String openId, String lang) {
        if (StringUtils.isBlank(lang)) {
            lang = "zh_CN";
        }
        String url = String.format("https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=%s", getAccessToken(false), openId, lang);
        String result = HttpUtils.doGet(url);
        log.info("获得微信粉丝用户信息:" + result);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }

        return WxUserResult.fromJson(result);
    }

    public WxOpenIdResult getOpenIds(String nextOpenId) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s", getAccessToken(false), nextOpenId);
        String result = HttpUtils.doGet(url);
        log.info("获得微信粉丝用户列表:" + result);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }

        return WxOpenIdResult.fromJson(result);
    }

    public List<WxTagsResult> getTags() {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/tags/get?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doGet(url);
        log.info("获得微信粉丝用户列表:" + result);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }

        return WxTagsResult.fromJsonList(result);
    }

    public WxTagsResult createTags(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/tags/create?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doPost(url, json);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }

        return WxTagsResult.fromJson(result);
    }

    public boolean updateTags(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/tags/update?access_token=%s", getAccessToken(false));
        String result = HttpUtils.doPost(url, json);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }
        return true;
    }

    public boolean deleteTags(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=%s", getAccessToken(false));

        String result = HttpUtils.doPost(url, json);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }

        return true;
    }

    public boolean batchTagging(String json) {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=%s", getAccessToken(false));

        String result = HttpUtils.doPost(url, json);
        WxMpError error = WxMpError.fromJson(result);
        if (error.getCode() != 0) {
            throw new WxMpException(error);
        }

        return true;
    }
}
