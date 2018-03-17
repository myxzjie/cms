package com.xzjie.et.cms.dao;

import com.xzjie.et.cms.model.Article;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.page.PageEntity;

import java.util.List;

/**
 * Created by xzjie on 2017/8/29.
 */
public interface ArticleMapper extends BaseMapper<Article, Long> {


    List<Article> selectRecommendList(Article model);

    List<Article> selectArticleListPage(PageEntity<Article> t);

}
