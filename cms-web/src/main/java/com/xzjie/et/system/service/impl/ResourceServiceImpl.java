package com.xzjie.et.system.service.impl;

import com.xzjie.et.system.dao.ResourceMapper;
import com.xzjie.et.system.model.Resource;
import com.xzjie.et.system.service.ResourceService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xzjie on 2017/8/9.
 */
@Service("resourceService")
public class ResourceServiceImpl extends AbstractBaseService<Resource, Long> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    protected BaseMapper<Resource, Long> getMapper() {
        return resourceMapper;
    }


    @Override
    public List<Resource> getResourceByRoleId(Long roleId) {
        return resourceMapper.selectResourceByRoleId(roleId);
    }
}
