package com.xzjie.mybatis.core.dao;

import com.xzjie.mybatis.page.PageEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T, Obj extends Serializable> {

	int deleteByPrimaryKey(Obj id);

    int batchDelete(List<T> list);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Obj id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    List<T> selectListPage(PageEntity<T> entity);
    
    List<T> selectList(T t);
}
