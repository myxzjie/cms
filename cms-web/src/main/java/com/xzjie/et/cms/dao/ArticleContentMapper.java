package com.xzjie.et.cms.dao;


import com.xzjie.et.cms.model.ArticleContent;
import com.xzjie.mybatis.core.dao.BaseMapper;

public interface ArticleContentMapper extends BaseMapper<ArticleContent, Long> {

	ArticleContent selectArticleContent(Long articleId);

	int updateByArticleId(ArticleContent model);
}