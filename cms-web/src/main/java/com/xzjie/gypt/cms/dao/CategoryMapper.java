package com.xzjie.gypt.cms.dao;

import java.util.List;

import com.xzjie.gypt.cms.model.Category;
import com.xzjie.gypt.common.dao.BaseMapper;

public interface CategoryMapper extends BaseMapper<Category, Long>{
   /* int deleteByPrimaryKey(Long categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);*/
	
	List<Category> findCategoryTree(Category record);
}