package com.xzjie.et.system.dao;

import com.xzjie.et.system.model.Role;
import com.xzjie.mybatis.core.dao.BaseMapper;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role,Long>{

    boolean exist(Role model);

    List<Role> selectRoleByUserId(Long userId);
}