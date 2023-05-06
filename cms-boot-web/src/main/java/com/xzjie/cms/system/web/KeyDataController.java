package com.xzjie.cms.system.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dict.service.KeyDataService;
import com.xzjie.cms.enums.KeyDataKey;
import com.xzjie.cms.dict.model.KeyData;
import com.xzjie.cms.wechat.service.WechatService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/key-data")
@Api(value = "管理端-系统配置数据",tags = "管理端-系统配置数据")
public class KeyDataController {
    //    private final String WECHAT_MENUS_KEY = "wechat_menus";
    @Autowired
    private KeyDataService keyDataService;
    @Autowired
    private WechatService wechatService;

    @GetMapping("/{key}")
    public Map<String, Object> getKeyData(@PathVariable String key) {
        KeyData keyData = keyDataService.getKeyDataByKey(key);
        return MapUtils.create().set("code", 0).set("data", keyData);
    }


    @PostMapping("/create/{key}")
    @PreAuthorize("@permission.hasPermission('administrator','wx-setting:all','wx-setting:edit')")
    public Map<String, Object> create(@PathVariable KeyDataKey key, @RequestBody String data) {

        KeyData keyData = new KeyData();
        keyData.setKey(key.name());

        switch (key) {
            case wechat_setting:
                keyData.setData(data);
                keyDataService.save(keyData);
                break;
            case wechat_menus:
                JSONObject json = JSON.parseObject(data);
                String buttons = json.get("button").toString();
                keyData.setData(buttons);

                // 创建菜单
                wechatService.deleteButton();
                wechatService.createButton(data);

                //保存数据
                keyDataService.save(keyData);
                break;
        }


        return MapUtils.create().set("code", 0);
    }


}
