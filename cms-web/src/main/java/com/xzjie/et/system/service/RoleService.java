package com.xzjie.et.system.service;

import com.xzjie.et.system.model.Role;
import com.xzjie.et.system.model.RoleResource;
import com.xzjie.mybatis.core.service.BaseService;

import java.util.List;

/**
 * Created by xzjie on 2017/8/13.
 */
public interface RoleService extends BaseService<Role,Long>{

    List<RoleResource> getRoleResourceByRoleId(Long roleId);

    boolean saveRoleResource(RoleResource model);

    boolean deleteRoleResource(Long roleId,Long resourceId);

    boolean isExistByRoleCode(String roleCode);

    Role getRoleByUserId(Long userId);
}
