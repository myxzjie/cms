package com.xzjie.gypt.system.dao;

import java.util.List;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.system.model.RoleResource;

public interface RoleResourceMapper extends BaseMapper<RoleResource, Long>{
	
	int deleteRoleResourceByRoleId(Long roleId);
	
	List<RoleResource> findRoleResourceByRoleId(Long roleId);
    /* 

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);*/
}