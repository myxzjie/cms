/**
 * radp-cms
 * @Title: RoleServiceImpl.java 
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月21日
 */
package com.xzjie.gypt.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.system.dao.RoleMapper;
import com.xzjie.gypt.system.model.Role;
import com.xzjie.gypt.system.service.RoleService;

/**
 * @className RoleServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月21日 下午10:14:52 
 * @version V0.0.1 
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public boolean save(Role record) {
		record.setCreateDate(new Date());
		record.setState(1); //状态 1正常，0失败
		int row=roleMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public void batchSave(List<Role> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Role record) {
		int row=roleMapper.update(record);
		return row>0?true:false;
	}

	@Override
	public void batchUpdate(List<Role> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		Role record=new Role();
		record.setRoleId(id);
		record.setState(0); //状态 1正常，0失败
		return this.update(record);
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Role record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<Role> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role get(Long id) {
		return roleMapper.getById(id);
	}

	@Override
	public Role get(Role record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getList(Role record) {
		return roleMapper.findList(record);
	}

	@Override
	public List<Role> getAllList(Role record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getListPage(PageEntity<Role> record) {
		return roleMapper.findListPage(record);
	}

	@Override
	public List<Role> getUserRoles(Long orgId, Long userId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("userId", userId);
		return roleMapper.findUserRoles(map);
	}

}
