package com.xzjie.et.system.service.impl;

import com.xzjie.et.system.dao.RoleMapper;
import com.xzjie.et.system.dao.RoleResourceMapper;
import com.xzjie.et.system.model.Role;
import com.xzjie.et.system.model.RoleResource;
import com.xzjie.et.system.service.RoleService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xzjie on 2017/8/13.
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractBaseService<Role, Long> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    protected BaseMapper<Role, Long> getMapper() {
        return roleMapper;
    }


    @Override
    public List<RoleResource> getRoleResourceByRoleId(Long roleId) {
        RoleResource model = new RoleResource();
        model.setRoleId(roleId);
        return roleResourceMapper.selectList(model);
    }

    @Override
    public boolean saveRoleResource(RoleResource model) {
        if (!roleResourceMapper.exist(model)) {
            return roleResourceMapper.insertSelective(model) > 0;
        }
        return false;
    }

    @Override
    public boolean deleteRoleResource(Long roleId, Long resourceId) {
        RoleResource model = new RoleResource();
        model.setRoleId(roleId);
        model.setResourceId(resourceId);
        return roleResourceMapper.deleteByRoleIdAndResourceId(model) > 0;
    }

    @Override
    public boolean isExistByRoleCode(String roleCode) {
        Role model = new Role();
        model.setRoleCode(roleCode);
        return roleMapper.exist(model);
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        List<Role> list = roleMapper.selectRoleByUserId(userId);
        return list.get(0);
    }

    @Override
    public boolean delete(Long id) {
        Role model = new Role();
        model.setRoleId(id);
        model.setState(0);
        return update(model);
    }


}
