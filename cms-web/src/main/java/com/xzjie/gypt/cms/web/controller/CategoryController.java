package com.xzjie.gypt.cms.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.cms.model.Category;
import com.xzjie.gypt.cms.model.Site;
import com.xzjie.gypt.cms.service.CategoryService;
import com.xzjie.gypt.cms.service.SiteService;
import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.web.controller.BaseController;

@Controller
@RequestMapping("category")
public class CategoryController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SiteService siteService;

	@RequestMapping(value="index")
	public String indexView(){
		return "cms/category/category_index";
	}
	
	@RequestMapping("edit")
	public String editView(Long id,Map<String, Object> modelMap){
		if(id!=null){
			Category category = categoryService.get(id);
			if (category.getCategoryPId() == 0) {
				category.setCategoryPId(-1L);
			}
			
			modelMap.put("model", category);
		}
		Site site = siteService.getSiteByOrgId(getPrincipal().getOrgId());
		modelMap.put("siteId", site.getSiteId());
		return "cms/category/category_edit";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Category model) {

		try {
			if (model.getCategoryPId() == -1) {
				model.setCategoryPId(0L);
			}

			boolean b = categoryService.save(model);
			if (b) {
				return MapResult.mapOK(RspCode.R00000);
			}
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}

		return MapResult.mapError(RspCode.R99998);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(Category model) {
		try {
			if (model.getCategoryPId() == -1) {
				model.setCategoryPId(0L);
			}
			categoryService.update(model);
			return MapResult.mapOK(RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}
	}
	
	@RequestMapping(value = "datapage")
	@ResponseBody
	public Map<String, Object> dataPage(Category record, Page page) {
		PageEntity<Category> pageEntity = new PageEntity<Category>();
		Site site = siteService.getSiteByOrgId(getPrincipal().getOrgId());
		
		record.setSiteId(site.getSiteId());
		
		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Category> resources = categoryService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
	}
	
	@RequestMapping(value = "tree", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> resourceTree(Long siteId) {
		List<Category> categorys = categoryService.getCategoryTree(0L, siteId);

		Category category = new Category();
		category.setCategoryId(-1L);
		category.setCategoryPId(0L);
		category.setCategoryName("选择");

		categorys.add(category);
		return MapResult.mapOK(categorys);
	}
	
	
}
