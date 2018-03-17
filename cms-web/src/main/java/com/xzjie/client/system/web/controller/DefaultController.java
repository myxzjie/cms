package com.xzjie.client.system.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzjie.client.core.web.BaseController;
import com.xzjie.et.cms.model.Article;
import com.xzjie.et.cms.service.ArticleService;

/**
 * Created by xzjie on 2017/8/7.
 */
@Controller
public class DefaultController extends BaseController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = { "", "/", "index" })
	public String index(Map<String, Object> model) {
		List<Article> list = articleService.getArticleLatestListPage(getSiteId(), 2);
		List<Article> recommends = articleService.getArticleRecommendList(getSiteId(), null, 5);
		model.put("articleLatest", list);
		model.put("recommends", recommends);
		return getRemoteView("index");
	}

}
