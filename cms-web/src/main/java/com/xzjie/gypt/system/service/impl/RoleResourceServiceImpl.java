/**
 * radp-cms
 * @Title: RoleResourceServiceImpl.java 
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月28日
 */
package com.xzjie.gypt.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.system.dao.RoleResourceMapper;
import com.xzjie.gypt.system.model.RoleResource;
import com.xzjie.gypt.system.service.RoleResourceService;

/**
 * @className RoleResourceServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月28日 上午10:21:37 
 * @version V0.0.1 
 */
@Service("roleResourceService")
public class RoleResourceServiceImpl implements RoleResourceService{
	
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public boolean save(RoleResource record) {
		int row=roleResourceMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public void batchSave(List<RoleResource> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(RoleResource record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchUpdate(List<RoleResource> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(RoleResource record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<RoleResource> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoleResource get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleResource get(RoleResource record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleResource> getList(RoleResource record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleResource> getAllList(RoleResource record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleResource> getListPage(PageEntity<RoleResource> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(List<RoleResource> roleResources, Long roleId) {
		if(roleResources==null){
			return;
		}
		//删除
		roleResourceMapper.deleteRoleResourceByRoleId(roleId);
		//添加
		for (RoleResource roleResource : roleResources) {
			roleResource.setRoleId(roleId);
			this.save(roleResource);
		}
		
	}

	@Override
	public List<RoleResource> getRoleResourceByRoleId(Long roleId) {
		return roleResourceMapper.findRoleResourceByRoleId(roleId);
	}

}
