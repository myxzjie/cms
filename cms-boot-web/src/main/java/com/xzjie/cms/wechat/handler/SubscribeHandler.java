package com.xzjie.cms.wechat.handler;

import com.xzjie.cms.wechat.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author vito
 * @since 2023/6/28 5:09 PM
 */
@Component
public class SubscribeHandler extends AbstractHandler {
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
//        String eventKey = wxMpXmlMessage.getEventKey();
//        String scanQrcodeInfo = eventKey.startsWith("qrscene_") ? eventKey.replace("qrscene_", "") : eventKey;
//        String qrcodeTicket = scanQrcodeInfo.replaceAll("lqr_", "");
//        String sessionCode = RandomUtil.generateDeviceUuid();
//        userLoginSessionService.updateSessionCode(qrcodeTicket, sessionCode, wxMessage.getFromUser());
//        return msg("登录成功", wxMpXmlMessage);
       return new TextBuilder().build("关注成功", wxMessage, wxMpService);
    }
}
