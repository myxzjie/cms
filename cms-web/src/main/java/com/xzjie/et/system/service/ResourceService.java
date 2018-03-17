package com.xzjie.et.system.service;

import com.xzjie.et.system.model.Resource;
import com.xzjie.mybatis.core.service.BaseService;

import java.util.List;

/**
 * Created by xzjie on 2017/8/9.
 */
public interface ResourceService extends BaseService<Resource, Long> {

    List<Resource> getResourceByRoleId(Long roleId);
}
