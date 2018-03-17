package com.xzjie.mybatis.core.service;

import java.io.Serializable;
import java.util.List;

import com.xzjie.mybatis.page.PageEntity;


public abstract interface BaseService<T,Obj extends Serializable> {

	boolean save(T t);
	
	void batchSave(List<T> list);

	boolean update(T t);
	
	void batchUpdate(List<T> list);
	
	boolean delete(Obj id);
	
	boolean delete(T t);
	
	void batchDelete(List<T> list);
	
	T get(Obj id);
	
	T get(T t);
	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param t
	 * @return
	 */
	List<T> getList(T t);
	
	/**
	 * 查询所有数据列表
	 * @param t
	 * @return
	 */
	List<T> getAllList(T t);
	
	/**
	 * 分页查询数据列表
	 * @param entity
	 * @return
	 */
	PageEntity<T> getListPage(PageEntity<T> pageEntity);
	
}