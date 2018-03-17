package com.xzjie.et.system.dao;

import com.xzjie.et.system.model.Resource;
import com.xzjie.mybatis.core.dao.BaseMapper;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource, Long> {

    List<Resource> selectResourceByRoleId(Long roleId);
}