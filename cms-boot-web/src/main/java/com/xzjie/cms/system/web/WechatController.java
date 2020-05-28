package com.xzjie.cms.system.web;

import com.xzjie.cms.service.impl.WechatService;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api/wechat")
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @ApiOperation(value = "微信验证消息", notes = "微信验证消息")
    @GetMapping("/serve")
    public String signature(@RequestParam(name = "signature", required = false) String signature,
                            @RequestParam(name = "timestamp", required = false) String timestamp,
                            @RequestParam(name = "nonce", required = false) String nonce,
                            @RequestParam(name = "echostr", required = false) String echostr) {
        final WxMpService wxMpService = wechatService.create();
        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "fail";
    }

    @ApiOperation(value = "微信获取消息", notes = "微信获取消息")
    @PostMapping("/serve")
    public void signature(@RequestBody String requestBody,
                          @RequestParam("signature") String signature,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce") String nonce,
                          @RequestParam("openid") String openid,
                          @RequestParam(name = "encrypt_type", required = false) String encType,
                          @RequestParam(name = "msg_signature", required = false) String msgSignature,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        final WxMpService wxMpService = wechatService.create();

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage message = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.route(wxMpService, message);
            if (outMessage == null) return;
            out = outMessage.toXml();
            ;
        } else if ("aes".equalsIgnoreCase(encType)) {
            // aes加密的消息
            WxMpXmlMessage message = WxMpXmlMessage.fromEncryptedXml(requestBody, wxMpService.getWxMpConfigStorage(),
                    timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = this.route(wxMpService, message);
            if (outMessage == null) return;

            out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
        }

        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(out);
        writer.close();
    }

    private WxMpXmlOutMessage route(WxMpService wxMpService, WxMpXmlMessage message) {
        WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        WxMpXmlOutMessage outMessage = router.route(message);

        return outMessage;
    }
}
