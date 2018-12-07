package com.xzjie.et.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.xzjie.et.BaseTest;
import com.xzjie.et.wechat.entity.TemplateData;
import com.xzjie.et.wechat.entity.TemplateMessage;
import com.xzjie.et.wechat.entity.WxAccessToken;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.service.impl.WechatHelper;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2018-12-05 21:00
 **/
public class WechatHelperTest extends BaseTest {

    @Autowired
    private WechatHelper wechatHelper;
    @Autowired
    private WxAccountService wxAccountService;

    @Test
    public void getTemplate() {
        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(1L);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);
        wechatHelper.getTemplate(accessToken.getAccess_token());
    }

    @Test
    public void getTemplateList() {
        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(1L);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);
        wechatHelper.getTemplateList(accessToken.getAccess_token());
    }

    @Test
    public void setIndustry() {
        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(1L);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);

        Map<String, Object> params = new HashMap<>();
        params.put("industry_id1", 1);
        params.put("industry_id2", 2);
        //params.put("industry_id3",4);
        wechatHelper.setIndustry(accessToken.getAccess_token(), params);

        //{{first.DATA}} 欢迎关注我们的公众号开发者{{message.DATA}}
        //{{remark.DATA}}感谢您的光临
    }

    @Test
    public void messageTemplateSend() {
        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(1L);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);

        TemplateMessage tplMsg = new TemplateMessage();

        String json = TemplateData.New()
                .setTouser("oDJmCwW3A5oEkL9HRc6VF6h8LZl0")
                .setTemplate_id("0eykeX1xRER1byoxXYSqSzbP-1jgFFRqS129VkV8V_4")
                .setTopcolor("#93b7f3")
                .setUrl("https://www.dev56.com")
                .add("first", "欢迎关注我们的公众号", "#173177")
                .add("message", "鹰视科技", "#173177")
                .add("remark", "感谢您的光临", "#173177")
                .build();


        wechatHelper.messageTemplateSend(accessToken.getAccess_token(), json);


    }
}
