package com.xzjie.gypt.cms.web.front.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "edit/{id}")
	public String editBlog(@PathVariable Long id,Map<String, Object> modelMap){
		Article article= articleService.get(id);
		article.setContent(this.unescapeHtml4(article.getContent()));
		modelMap.put("model", article);
		return "front/editblog";
	}
	
	@RequestMapping(value = "add")
	@ResponseBody
	public Map<String, Object> addBlog(Article model){
		
		try {
			model.setAuthor(getUserId());
			model.setSort(0);
			
			if(model.getApproveStatus()==1){
				//model.setPublishDate(new Date());
				//model.setp
			}
			
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

		
		return MapResult.mapOK(categorys);
	}
	
	@RequestMapping(value = "category/{approveStatus}/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> category(@PathVariable Integer approveStatus,@PathVariable Long id) {
		Article record=new Article();
		
		record.setArticleId(id);
		record.setApproveStatus(approveStatus);
		try {
			
			if(articleService.update(record)){
				return MapResult.mapOK(RspCode.R00000,"成功");
			}else{
				return MapResult.mapError(RspCode.R99998, "失败");
			}
			
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}
	}
}
