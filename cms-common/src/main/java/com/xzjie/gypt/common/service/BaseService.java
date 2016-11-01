package com.xzjie.gypt.common.service;

import java.io.Serializable;
import java.util.List;

import com.xzjie.gypt.common.page.PageEntity;



public abstract interface BaseService<T,Obj extends Serializable> {

	boolean save(T record);
	
	void batchSave(List<T> records);

	boolean update(T record);
	
	void batchUpdate(List<T> records);
	
	boolean delete(Obj id);
	
	@Deprecated
	void batchDeleteById(List<Obj> records);
	
	boolean delete(T record);
	
	void batchDelete(List<T> records);
	
	T get(Obj id);
	
	T get(T record);
	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	List<T> getList(T record);
	
	/**
	 * 查询所有数据列表
	 * @param entity
	 * @return
	 */
	List<T> getAllList(T record);
	
	/**
	 * 分页查询数据列表
	 * @param entity
	 * @return
	 */
	List<T> getListPage(PageEntity<T> record);
	
}
