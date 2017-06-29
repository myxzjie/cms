package com.xzjie.gypt.cms.web.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xzjie.cache.SystemCacheManager;
import com.xzjie.gypt.cms.model.Category;
import com.xzjie.gypt.cms.model.Site;
import com.xzjie.gypt.cms.service.CategoryService;

/**
 * CategoryTag 不使用 ，推荐使用 freemarker 自定义标签 NavsDirective 
 * @author xzjie
 *
 */
@Deprecated
@Component
public class CategoryTag {

	private static final String SITE_KEY = "site";

	private static CategoryService categoryService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		CategoryTag.categoryService = categoryService;
	}

	@SuppressWarnings("unchecked")
	public static List<Category> getNavs() {
		String key = "categorys";
		List<Category> list = null;
		try {
			list = (List<Category>) SystemCacheManager.get(key);
		} catch (Exception e) {
			Site site = (Site) SystemCacheManager.get(SITE_KEY);
			list = categoryService.getCategoryTree(null, site.getSiteId());
			SystemCacheManager.put(key, list);
		}

		return list;
	}
	
}
