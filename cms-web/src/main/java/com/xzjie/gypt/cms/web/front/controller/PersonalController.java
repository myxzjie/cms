package com.xzjie.gypt.cms.web.front.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzjie.gypt.cms.model.Article;
import com.xzjie.gypt.cms.service.ArticleService;
import com.xzjie.gypt.cms.service.CategoryService;
import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.system.web.controller.BaseController;

@Controller
@RequestMapping(value = "${web.frontPath}/personal")
public class PersonalController extends BaseController{
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "info")
	public String info(Article model,Page page,Map<String, Object> modelMap){
		categoryService.setCategoryList(getSiteId(), modelMap);
		
		PageEntity<Article> record=new PageEntity<>();
		
		model.setSiteId(getSiteId());
		model.setAuthor(this.getUserId());
		
		record.setPage(page);
		record.setRecord(model);
		
		List<Article>  articles=articleService.getListPage(record);
		
		modelMap.put("articles", articles);
		modelMap.put("totalPage", page.getTotalPage());
		
		return "front/personalinfo";
	}
}
