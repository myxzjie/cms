package com.xzjie.et.cms.service;

import com.xzjie.et.cms.model.Article;
import com.xzjie.et.cms.model.ArticleContent;
import com.xzjie.mybatis.core.service.BaseService;
import com.xzjie.mybatis.page.PageEntity;

import java.util.List;


public interface ArticleService extends BaseService<Article, Long> {


    PageEntity<Article> getArticlePage(Long categoryId, int currentPage);

    ArticleContent getArticleContent(Long articleId);

    /**
     * @param categoryId
     * @param type       null 全部，0 不推荐，1 推荐
     * @return
     */
    List<Article> getRecommendList(Long categoryId, Integer type);


    List<Article> getArticleRecommendList(Long siteId, Long categoryId, int showCount);

    /**
     * 获得内容数据包括categoryId子类的内容
     *
     * @param categoryId
     * @param currentPage
     * @param showCount
     * @return
     */
    PageEntity<Article> getArticleListPage(Long categoryId, int currentPage, int showCount);

    /**
     * @param siteId
     * @param categoryId null 不分
     * @param showCount
     * @param type       null 全部，0 不推荐，1 推荐
     * @return
     */
    PageEntity<Article> getArticleListPage(Long siteId, Long categoryId, Integer type, int currentPage, int showCount);

    List<Article> getArticleList(Long siteId, Long categoryId, Integer type, int showCount);

    /**
     * 获得最新动态
     *
     * @param siteId
     * @param showCount
     * @return
     */
    List<Article> getArticleLatestListPage(Long siteId, int showCount);
}
