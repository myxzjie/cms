package com.xzjie.et.system.dao;

import com.xzjie.et.system.model.RoleResource;
import com.xzjie.mybatis.core.dao.BaseMapper;

import java.util.List;

public interface RoleResourceMapper extends BaseMapper<RoleResource,Long>{

    boolean exist(RoleResource t);

    int deleteByRoleIdAndResourceId(RoleResource roleResource);
}