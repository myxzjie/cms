package com.xzjie.gypt.cms.dao;

import com.xzjie.gypt.cms.model.ArticleContent;
import com.xzjie.gypt.common.dao.BaseMapper;

public interface ArticleContentMapper extends BaseMapper<ArticleContent, Long>{
	
	int updateByArticleId(ArticleContent record);
}