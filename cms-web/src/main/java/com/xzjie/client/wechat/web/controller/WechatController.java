package com.xzjie.client.wechat.web.controller;

import com.xzjie.client.core.web.BaseController;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.service.WxAccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Controller
public class WechatController extends BaseController {

    @Autowired
    private WxAccountService wxAccountService;

    @RequestMapping(value = "wechat/{code}", method = RequestMethod.GET)
    public void signature(@PathVariable String code,
                          @RequestParam(value = "signature", required = true) String signature,
                          @RequestParam(value = "timestamp", required = true) String timestamp,
                          @RequestParam(value = "nonce", required = true) String nonce,
                          @RequestParam(value = "echostr", required = true) String echostr, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        WxAccount model = wxAccountService.getWxAccountBySiteId(getSiteId());

        if (model == null) {
            writer.print("code error");
            return;
        }

        String[] values = {model.getToken(), timestamp, nonce};

        Arrays.sort(values); // 字典序排序

        String value = values[0] + values[1] + values[2];

        String sign = DigestUtils.sha1Hex(value);


        if (signature.equals(sign)) {// 验证成功返回ehcostr
            writer.print(echostr);
        } else {
            writer.print("error");
        }

        writer.flush();

        writer.close();

    }

//    @RequestMapping(value="accesstoken")
//    @ResponseBody
//    public WXAccessToken getAccessToken(){
//        WXAccount model = wxAccountService.get(getWXAppid());
//        return wechatHelper.getAccessToken(model.getAppid(), model.getAppsecret(), request.getSession());
//    }
}
