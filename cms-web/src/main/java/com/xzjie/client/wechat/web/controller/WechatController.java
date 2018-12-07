package com.xzjie.client.wechat.web.controller;

import com.xzjie.client.core.web.BaseController;
import com.xzjie.common.web.utils.MapResult;
import com.xzjie.et.wechat.entity.TemplateData;
import com.xzjie.et.wechat.entity.TemplateMessage;
import com.xzjie.et.wechat.entity.WxAccessToken;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.service.WxAccountService;
import com.xzjie.et.wechat.service.impl.WechatHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WechatController extends BaseController {

    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private WechatHelper wechatHelper;

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

    /**
     * 接收来自微信发来的消息
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "wechat/{code}", method = RequestMethod.POST)
    @ResponseBody
    public void wechatEvent(HttpServletRequest request, HttpServletResponse response) {
        try {
            InputStream inputStream = request.getInputStream();

            String res = IOUtils.toString(inputStream);

            System.out.println("接收来自微信发来的消息>>." + res);
            PrintWriter writer = response.getWriter();
            writer.print(res);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析微信请求并读取XML
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> readWeixinXml(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<String,String>();
        //获取输入流
        InputStream input = request.getInputStream();
        //使用dom4j的SAXReader读取（org.dom4j.io.SAXReader;）
        SAXReader sax = new SAXReader();
        Document doc = sax.read(input);
        //获取XML数据包根元素
        Element root = doc.getRootElement();
        //得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        //遍历所有节点并将其放进map
        for(Element e : elementList){
            map.put(e.getName(), e.getText());
        }
        //释放资源
        input.close();
        input = null;
        return map;

    }



    @RequestMapping(value = "wechat/message")
    @ResponseBody
    public Map<String, Object> messageTemplateSend() {
        WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(1L);
        WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);

        String json = TemplateData.New()
                .setTouser("oDJmCwW3A5oEkL9HRc6VF6h8LZl0")
                .setTemplate_id("0eykeX1xRER1byoxXYSqSzbP-1jgFFRqS129VkV8V_4")
                .setTopcolor("#93b7f3")
                .setUrl("https://www.dev56.info")
                .add("first", "欢迎关注我们的公众号", "#173177")
                .add("message", "鹰视科技", "#173177")
                .add("remark", "感谢您的光临", "#173177")
                .build();


        wechatHelper.messageTemplateSend(accessToken.getAccess_token(), json);

        return MapResult.mapOK();

    }

}
