package com.xzjie.et.wechat.service.impl;

import com.xzjie.et.wechat.dao.WxGroupMapper;
import com.xzjie.et.wechat.model.WxGroup;
import com.xzjie.et.wechat.service.WxGroupService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2018-12-09 20:10
 **/
@Service("wxGroupService")
public class WxGroupServiceImpl extends AbstractBaseService<WxGroup, Long> implements WxGroupService {

    @Autowired
    private WxGroupMapper wxGroupMapper;

    @Override
    protected BaseMapper<WxGroup, Long> getMapper() {
        return wxGroupMapper;
    }


    @Override
    public List<WxGroup> getWxGroupList(Long siteId) {
        WxGroup group = new WxGroup();
        group.setSiteId(siteId);
        return getList(group);
    }
}
