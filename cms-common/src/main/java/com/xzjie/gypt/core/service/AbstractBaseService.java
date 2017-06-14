package com.xzjie.gypt.core.service;

import java.io.Serializable;
import java.util.List;

import com.xzjie.gypt.common.dao.BaseMapper;
import com.xzjie.gypt.common.page.PageEntity;


public abstract class AbstractBaseService <T, Obj extends Serializable> implements BaseService<T, Obj> {

	protected abstract BaseMapper<T, Obj> getMapper();
	
	
	@Override
	public boolean save(T record) {
		return getMapper().insert(record) >0 ;
	}

	@Override
	public void batchSave(List<T> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(T record) {
		return getMapper().update(record) > 0;
	}

	@Override
	public void batchUpdate(List<T> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Obj id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDeleteById(List<Obj> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(T record) {
		return getMapper().delete(record) > 0;
	}

	@Override
	public void batchDelete(List<T> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T get(Obj id) {
		return getMapper().getById(id);
	}

	@Override
	public T get(T record) {
		return getMapper().getEntity(record);
	}

	@Override
	public List<T> getList(T record) {
		return getMapper().findList(record);
	}

	@Override
	public List<T> getAllList(T record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getListPage(PageEntity<T> record) {
		return getMapper().findListPage(record);
	}
}
