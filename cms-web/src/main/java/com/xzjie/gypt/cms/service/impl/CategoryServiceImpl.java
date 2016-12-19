package com.xzjie.gypt.cms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.cms.dao.CategoryMapper;
import com.xzjie.gypt.cms.model.Category;
import com.xzjie.gypt.cms.service.CategoryService;
import com.xzjie.gypt.common.page.PageEntity;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public boolean save(Category record) {
		record.setCreateDate(new Date());
		record.setState(1); // 1 正常 0失效
		return categoryMapper.insert(record) > 0;
	}

	@Override
	public void batchSave(List<Category> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update(Category record) {
		return categoryMapper.update(record) > 0;
	}

	@Override
	public void batchUpdate(List<Category> records) {
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
	public boolean delete(Category record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<Category> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public Category get(Long id) {
		return categoryMapper.getById(id);
	}

	@Override
	public Category get(Category record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getList(Category record) {
		return categoryMapper.findList(record);
	}

	@Override
	public List<Category> getAllList(Category record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getListPage(PageEntity<Category> record) {
		return categoryMapper.findListPage(record);
	}

	@Override
	public void setCategoryList(Long siteId, Map<String, Object> modelMap) {
		Category record = new Category();
		record.setSiteId(siteId);
		record.setIsShow("1"); //状态为1 栏目
		List<Category> navs=categoryMapper.findCategoryTree(record);
		modelMap.put("navs", navs);
	}

	@Override
	public List<Category> getCategoryTree(Long categoryPId, Long siteId) {
		Category record = new Category();
		record.setSiteId(siteId);
		// record.setCategoryId(categoryId);
		return categoryMapper.findCategoryTree(record);
	}

	@Override
	public Category getCategory(Long siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getCategoryChild(Long siteId) {
		Category record = new Category();
		record.setSiteId(siteId);
		return categoryMapper.findCategoryChild(record);
	}
}
