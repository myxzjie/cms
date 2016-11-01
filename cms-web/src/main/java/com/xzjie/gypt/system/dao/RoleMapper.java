package com.xzjie.gypt.system.dao;

import java.util.List;
import java.util.Map;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.system.model.Role;

public interface RoleMapper extends BaseMapper<Role, Long>{
   /* int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);*/
	List<Role> findUserRoles(Map<String, Object> map);
}