package com.xzjie.gypt.common.dao;

import java.io.Serializable;
import java.util.List;

import com.xzjie.gypt.common.page.PageEntity;






public abstract interface BaseMapper<T, Obj extends Serializable> {

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	T getById(Obj id);

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	T getEntity(T record);

	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * 
	 * @param entity
	 * @return
	 */
	List<T> findList(T record);

	/**
	 * 分页查询数据列表
	 * 
	 * @param entity
	 * @return
	 */
	List<T> findListPage(PageEntity<T> record);

	/**
	 * 查询所有数据列表
	 * 
	 * @param entity
	 * @return
	 */
	List<T> findAllList(T record);

	/**
	 * 查询所有数据列表
	 * 
	 * @see public List<T> findAllList(T entity)
	 * @return
	 */
	@Deprecated
	List<T> findAllList();

	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @return
	 */
	int insert(T record);

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 */
	int update(T record);

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @see public int delete(T entity)
	 * @return
	 */
	@Deprecated
	int delete(Obj id);

	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * 
	 * @param entity
	 * @return
	 */
	int delete(T record);
}
