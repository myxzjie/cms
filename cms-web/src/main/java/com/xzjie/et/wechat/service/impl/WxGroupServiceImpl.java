package com.xzjie.et.wechat.service.impl;

import com.xzjie.et.wechat.dao.WxGroupFollowMapper;
import com.xzjie.et.wechat.dao.WxGroupMapper;
import com.xzjie.et.wechat.model.WxGroup;
import com.xzjie.et.wechat.model.WxGroupFollow;
import com.xzjie.et.wechat.service.WxGroupService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private WxGroupFollowMapper wxGroupFollowMapper;

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

    @Override
    public boolean batchGroupFollow(Long groupId, List<Long> followids) {
        List<WxGroupFollow> list = new ArrayList<>();
        for (Long followId : followids) {
            //已存在
            if (exist(groupId, followId)) {
                continue;
            }
            WxGroupFollow model = new WxGroupFollow();
            model.setFollowId(followId);
            model.setGroupId(groupId);
            list.add(model);
        }
        if (list.size() < 1) {
            return false;
        }
        return wxGroupFollowMapper.batchInsert(list) > 0;
    }

    public boolean exist(Long groupId, Long followId) {
        WxGroupFollow model = new WxGroupFollow();
        model.setFollowId(followId);
        model.setGroupId(groupId);
        return wxGroupFollowMapper.exist(model) > 0;
    }
}
