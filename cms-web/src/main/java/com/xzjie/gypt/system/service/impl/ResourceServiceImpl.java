/**
 * radp-cms
 * @Title: ResourceServiceImpl.java 
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月2日
 */
package com.xzjie.gypt.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.system.dao.ResourceMapper;
import com.xzjie.gypt.system.model.Resource;
import com.xzjie.gypt.system.service.ResourceService;

/**
 * @className ResourceServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月2日 下午12:19:04 
 * @version V0.0.1 
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{
	
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public boolean save(Resource record) {
		record.setCreateDate(new Date());
		record.setState(1); //状态 1正常，0失败
		int row=resourceMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public void batchSave(List<Resource> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Resource record) {
		int row=resourceMapper.update(record);
		return row>0?true:false;
	}

	@Override
	public void batchUpdate(List<Resource> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		Resource record=new Resource();
		record.setResourceId(id);
		record.setState(0); //状态 1正常，0失败
		return this.update(record);
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Resource record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<Resource> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Resource get(Long id) {
		return resourceMapper.getById(id);
	}

	@Override
	public Resource get(Resource record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getList(Resource record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getAllList(Resource record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Resource> getListPage(PageEntity<Resource> record) {
		return resourceMapper.findListPage(record);
	}

	@Override
	public List<Resource> getResourceTree(Long resourceId) {
		return resourceMapper.findResourceTree(resourceId);
	}

	@Override
	public List<Resource> getResourceUser(Long userId) {
		return resourceMapper.findResourceUser(userId);
	}

	@Override
	public List<Resource> getResourceUserByUserId(Long userId) {
		return resourceMapper.findResourceUserByUserId(userId);
	}

}
