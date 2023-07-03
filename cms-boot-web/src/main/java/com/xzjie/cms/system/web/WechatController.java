package com.xzjie.cms.system.web;

import cn.hutool.core.util.RandomUtil;
import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.utils.XmlConvertUtil;
import com.xzjie.cms.wechat.dto.WxXmlMessage;
import com.xzjie.cms.wechat.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wechat")
@Api(value = "管理端-微信公众号管理", tags = "管理端-微信公众号管理")
public class WechatController {

    @Autowired
    private WechatService wechatService;
    private final WxMpService wxMpService;
    private final WxMpMessageRouter messageRouter;

    @ApiOperation(value = "微信验证消息", notes = "微信验证消息")
    @RequestMapping("/serve")
    public String signature(@RequestParam(name = "signature", required = false) String signature,
                            @RequestParam(name = "timestamp", required = false) String timestamp,
                            @RequestParam(name = "nonce", required = false) String nonce,
                            @RequestParam(name = "echostr", required = false) String echostr) {

        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "fail";
    }

    @ApiOperation(value = "微信获取消息", notes = "微信获取消息")
    @PostMapping("/serve")
    public void signature(@RequestBody String body,
                          @RequestParam("signature") String signature,
                          @RequestParam("timestamp") String timestamp,
                          @RequestParam("nonce") String nonce,
                          @RequestParam("openid") String openid,
                          @RequestParam(name = "encrypt_type", required = false) String encType,
                          @RequestParam(name = "msg_signature", required = false) String msgSignature,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }
        log.info(">>body:{}", body);
        String out = "";
        if ("aes".equalsIgnoreCase(encType)) {
            // aes加密的消息
            WxMpXmlMessage message = WxMpXmlMessage.fromEncryptedXml(body, wxMpService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            WxMpXmlOutMessage outMessage = messageRouter.route(message);
            if (outMessage == null) return;
            out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
        } else {
            // 明文传输的消息
            //<xml><ToUserName><![CDATA[gh_a484275dc078]]></ToUserName>
            //<FromUserName><![CDATA[ovOrn6DrX-H6aaWrFN8iTxs5bYXI]]></FromUserName>
            //<CreateTime>1687922474</CreateTime>
            //<MsgType><![CDATA[event]]></MsgType>
            //<Event><![CDATA[SCAN]]></Event>
            //<EventKey><![CDATA[t1tqen4a]]></EventKey>
            //<Ticket><![CDATA[gQHj8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyblJnVFZmWUdmREUxS3Z4QTFBY3gAAgQfp5tkAwSAOgkA]]></Ticket>
            //</xml>
            WxMpXmlMessage message = WxMpXmlMessage.fromXml(body);
            if ("event".equals(message.getMsgType())) {
                // 扫描二维码
                if ("SCAN".equals(message.getEvent())) {

                }
                // 关注
                if ("subscribe".equals(message.getEvent())) {

                }
                // 取消关注
                if ("unsubscribe".equals(message.getEvent())) {

                }

                WxMpXmlOutMessage outMessage = messageRouter.route(message);
                if (outMessage == null) return;
                out = outMessage.toXml();
            }
        }
        log.info(">>out:{}", out);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(out);
        writer.close();
    }


    @GetMapping("/qrcode/login")
    public Result<?> createLoginQrcode() {
        String sceneStr = "lqr_" + RandomUtil.randomString(8);
        try {
            WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(sceneStr, 604800);
            return Result.data(ticket);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return Result.fail(e.getMessage());
        }
    }

    @GetMapping("/openid")
    public Result<?> openid(String eventKey) {
//        if(loginMap.get(eventKey) == null){
//            return ResultJson.error("未扫码成功！") ;
//        }
//        CodeLoginKey codeLoginKey = loginMap.get(eventKey);
//        String openId = codeLoginKey.getOpenId();
//        loginMap.remove(eventKey);
        return Result.success();
    }

}
