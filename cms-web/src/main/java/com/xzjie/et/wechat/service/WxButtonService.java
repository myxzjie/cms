package com.xzjie.et.wechat.service;

import com.xzjie.et.wechat.model.WxButton;
import com.xzjie.mybatis.core.service.BaseService;

import java.util.List;

public interface WxButtonService extends BaseService<WxButton,Long>{

    List<WxButton> getTree(Long buttonId, Long userId);

    void syncButton(Long siteId) throws Exception;
}
