package com.xzjie.mybatis.core.service;

import java.io.Serializable;
import java.util.List;

import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.page.PageEntity;

public abstract class AbstractBaseService<T, Obj extends Serializable> implements BaseService<T, Obj> {

	protected abstract BaseMapper<T, Obj> getMapper();

	/*public void setMapper(BaseMapper<T, Obj> baseMapper) {
		this.mapper = baseMapper;
	}*/

	@Override
	public boolean save(T t) {
		return getMapper().insertSelective(t) > 0;
	}

	@Override
	public void batchSave(List<T> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update(T t) {
		return getMapper().updateByPrimaryKeySelective(t) > 0;
	}

	@Override
	public void batchUpdate(List<T> list) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(Obj id) {
		return getMapper().deleteByPrimaryKey(id) > 0;
	}

	@Override
	public boolean delete(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<T> list) {
		getMapper().batchDelete(list);
	}

	@Override
	public T get(Obj id) {
		return getMapper().selectByPrimaryKey(id);
	}

	@Override
	public T get(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getList(T t) {
		return getMapper().selectList(t);
	}

	@Override
	public List<T> getAllList(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageEntity<T> getListPage(PageEntity<T> pageEntity) {
		List<T> list=getMapper().selectListPage(pageEntity);
		pageEntity.setRows(list);
		return pageEntity;
	}

}
