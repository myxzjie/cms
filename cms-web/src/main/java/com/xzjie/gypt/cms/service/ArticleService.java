package com.xzjie.gypt.cms.service;

import java.util.List;

import com.xzjie.gypt.cms.model.Article;
import com.xzjie.gypt.cms.model.ArticleContent;
import com.xzjie.gypt.common.service.BaseService;

public interface ArticleService extends BaseService<Article, Long>{

	void save(Article record,String content);
	
	void update(Article record,String content);
	
	ArticleContent getContent(Long id);
	
	List<Article> sliderList(Article record);
	
	List<Article> getContentBySiteId(Long siteId);
}
