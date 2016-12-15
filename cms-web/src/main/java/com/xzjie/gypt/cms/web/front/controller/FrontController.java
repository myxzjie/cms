/**
 * radp-cms
 * @Title: FrontController.java 
 * @Package com.xzjie.gypt.cms.web.front
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月3日
 */
package com.xzjie.gypt.cms.web.front.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.cms.model.Article;
import com.xzjie.gypt.cms.model.Category;
import com.xzjie.gypt.cms.service.ArticleService;
import com.xzjie.gypt.cms.service.CategoryService;
import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.web.controller.BaseController;

/**
 * @className FrontController.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年8月3日 上午10:02:59
 * @version V0.0.1
 */
@Controller
@RequestMapping(value = "${web.frontPath}")
public class FrontController extends BaseController {

	// @Autowired
	// private SiteService siteService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "index")
	private String index(Article article, Page page, Map<String, Object> modelMap) {

		PageEntity<Article> record = new PageEntity<Article>();

		categoryService.setCategoryList(getSiteId(), modelMap);

		if (page.getCurrentPage() < 1) {
			page.setCurrentPage(1);
		}

		article.setSiteId(getSiteId());
		article.setApproveStatus(1);//发布的

		record.setPage(page);
		record.setRecord(article);

		modelMap.put("articles", articleService.getListPage(record));
		modelMap.put("page", page);
		modelMap.put("siteId", getSiteId());
		return "front/index";
	}

	@RequestMapping(value = "category")
	private String category(Long id, Map<String, Object> modelMap) {

		categoryService.setCategoryList(getSiteId(), modelMap);
		Category category = categoryService.get(id);

		PageEntity<Article> pageEntity = new PageEntity<Article>();
		Page page = new Page();
		Article article = new Article();
		// Site site = siteService.get(getSiteId());

		page.setCurrentPage(1);

		article.setCategoryId(id);
		article.setSiteId(getSiteId());
		article.setApproveStatus(1);//发布的
		
		pageEntity.setPage(page);
		pageEntity.setRecord(article);
		List<Article> list = articleService.getListPage(pageEntity);

		modelMap.put("model", category);
		modelMap.put("articles", list);
		modelMap.put("siteId", getSiteId());
		return "front/category_index";
	}

	/**
	 * 
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "article")
	private String article(Long id, Map<String, Object> modelMap) {
		//TODO 可以优化
		//访问量
		articleService.updateAccess(id);
		
		categoryService.setCategoryList(getSiteId(), modelMap);
		Article article = articleService.get(id);
		
		article.setContent(this.unescapeHtml4(article.getContent()));
		modelMap.put("model", article);
		return "front/article_index";
	}

	@RequestMapping(value = "slider")
	@ResponseBody
	public Map<String, Object> slider(Article record, Page page) {
		PageEntity<Article> pageEntity = new PageEntity<Article>();
		record.setSiteId(getSiteId());

		record.setRecommendType(1);

		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Article> resources = articleService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
	}
	
	@RequestMapping(value="recommend")
	@ResponseBody
	public Map<String,Object> getRecommend(Article record, Page page){
		PageEntity<Article> pageEntity = new PageEntity<Article>();
		record.setSiteId(getSiteId());

		page.setShowCount(5);
		record.setRecommendType(2);

		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Article> resources = articleService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
	}

}
