package com.xzjie.gypt.cms.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xzjie.gypt.cms.model.Article;
import com.xzjie.gypt.cms.model.ArticleContent;
import com.xzjie.gypt.cms.model.Site;
import com.xzjie.gypt.cms.service.ArticleService;
import com.xzjie.gypt.cms.service.SiteService;
import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.web.controller.BaseController;

@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private SiteService siteService;

	@RequestMapping(value = "index")
	public String indexView() {
		return "cms/article/article_index";
	}

	@RequestMapping("edit")
	public String editView(Long id, Map<String, Object> modelMap) {
		if (id != null) {
			Article article = articleService.get(id);
			article.setContent(this.unescapeHtml4(article.getContent()));
			modelMap.put("model", article);
		}
		Site site = siteService.getSiteByOrgId(getPrincipal().getOrgId());
		modelMap.put("siteId", site.getSiteId());
		return "cms/article/article_edit";
	}

	@RequestMapping(value = "content", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getContent(Long id) {

		try {
			ArticleContent articleContent = articleService.getContent(id);
			articleContent.setContent(this.unescapeHtml4(articleContent.getContent()));
			return MapResult.mapOK(articleContent, RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(Article model) {

		try {
			model.setAuthor(getUserId());
			articleService.save(model, model.getContent());
			return MapResult.mapOK(RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}

	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(Article model) {
		try {
			articleService.update(model, model.getContent());
			return MapResult.mapOK(RspCode.R00000);
		} catch (Exception e) {
			return MapResult.mapError(RspCode.R99998, e.getMessage());
		}
	}

	@RequestMapping(value = "datapage")
	@ResponseBody
	public Map<String, Object> dataPage(Article record, Page page) {
		PageEntity<Article> pageEntity = new PageEntity<Article>();
		Site site = siteService.getSiteByOrgId(getPrincipal().getOrgId());
		record.setSiteId(site.getSiteId());

		pageEntity.setRecord(record);
		pageEntity.setPage(page);
		List<Article> resources = articleService.getListPage(pageEntity);
		return MapResult.mapOK(resources, pageEntity.getPage(), "OK");
	}
}
