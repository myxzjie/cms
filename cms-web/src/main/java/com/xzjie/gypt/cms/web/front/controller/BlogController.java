package com.xzjie.gypt.cms.web.front.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.cms.model.Article;
import com.xzjie.gypt.cms.model.Category;
import com.xzjie.gypt.cms.service.ArticleService;
import com.xzjie.gypt.cms.service.CategoryService;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.web.controller.BaseController;

@Controller
@RequestMapping(value = "${web.frontPath}/blog")
public class BlogController extends BaseController{
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "myblog")
	public String myblog(){
		return "front/myblog";
	}
	
	@RequestMapping(value = "edit")
	public String editBlog(){
		return "front/editblog";
	}
	
	@RequestMapping(value = "add")
	@ResponseBody
	public Map<String, Object> addBlog(Article model){
		
		try {
			model.setAuthor(getUserId());
			articleService.save(model, model.getContent());
			return MapResult.mapOK(RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}
	}
	
	@RequestMapping(value = "category", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> category() {
		Long siteId=this.getSiteId();
		List<Category> categorys = categoryService.getCategoryChild(siteId);

		/*Category category = new Category();
		category.setCategoryId(null);
		category.setCategoryPId(0L);
		category.setCategoryName("选择");

		categorys.add(category);*/
		return MapResult.mapOK(categorys);
	}
}
