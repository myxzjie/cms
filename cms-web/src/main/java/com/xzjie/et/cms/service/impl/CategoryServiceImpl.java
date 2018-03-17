package com.xzjie.et.cms.service.impl;

import com.xzjie.et.cms.dao.CategoryMapper;
import com.xzjie.et.cms.model.Category;
import com.xzjie.et.cms.service.CategoryService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xzjie on 2017/8/18.
 */
@Service("categoryService")
public class CategoryServiceImpl extends AbstractBaseService<Category,Long> implements CategoryService{

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    protected BaseMapper<Category, Long> getMapper() {
        return categoryMapper;
    }

    @Override
    public boolean delete(Long id) {
        Category model=new Category();
        model.setCategoryId(id);
        model.setState(0);
        return update(model);
    }
}
