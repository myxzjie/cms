package com.xzjie.gypt.cms.service;

import java.util.List;
import java.util.Map;

import com.xzjie.gypt.cms.model.Category;
import com.xzjie.gypt.common.service.BaseService;

public interface CategoryService extends BaseService<Category, Long>{

	void setCategoryList(Long siteId, Map<String, Object> modelMap);
	
	List<Category> getCategoryTree(Long categoryPId,Long siteId);
	
	Category getCategory(Long siteId);
	
	List<Category> getCategoryChild(Long siteId); 
}
