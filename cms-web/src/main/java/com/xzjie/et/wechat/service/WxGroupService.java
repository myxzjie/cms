package com.xzjie.et.wechat.service;

import com.xzjie.et.wechat.model.WxGroup;
import com.xzjie.mybatis.core.service.BaseService;

import java.util.List;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2018-12-09 20:09
 **/
public interface WxGroupService extends BaseService<WxGroup, Long> {

    List<WxGroup> getWxGroupList(Long siteId);
}
