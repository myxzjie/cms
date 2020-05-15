package com.xzjie.cms.system.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.enums.KeyDataKey;
import com.xzjie.cms.model.KeyData;
import com.xzjie.cms.service.KeyDataService;
import com.xzjie.cms.service.factory.WechatServiceFactory;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequestMapping("/api/key-data")
public class KeyDataController {
    private final String WECHAT_MENUS_KEY = "wechat_menus";
    @Autowired
    private KeyDataService keyDataService;
    @Autowired
    private WechatServiceFactory factory;
    private  WxMpService wxMpService;
    @PostConstruct
    public void init() {
        wxMpService = factory.create();
    }

    @GetMapping("/{key}")
    public Map<String, Object> getKeyData(@PathVariable String key) {
        KeyData keyData = keyDataService.getKeyDataByKey(key);
        return MapUtils.create().set("code", 0).set("data", keyData);
    }

    @PostMapping("/create/{key}")
    public Map<String, Object> create(@PathVariable KeyDataKey key, @RequestBody String data) throws WxErrorException {

        KeyData keyData = new KeyData();
        keyData.setKey(key.name());

        switch (key) {
            case wechat_setting:
                keyData.setData(data);
                keyDataService.save(keyData);
                init();
                break;
            case wechat_menus:
                JSONObject json = JSON.parseObject(data);
                String buttons = json.get("buttons").toString();
                keyData.setData(buttons);

                WxMenu menu = JSONObject.parseObject(data, WxMenu.class);

                // 创建菜单
                wxMpService.getMenuService().menuDelete();
                wxMpService.getMenuService().menuCreate(menu);
                keyDataService.save(keyData);
                break;
        }


        return MapUtils.create().set("code", 0);
    }


}
