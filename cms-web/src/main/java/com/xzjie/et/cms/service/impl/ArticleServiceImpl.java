package com.xzjie.et.cms.service.impl;

import com.xzjie.et.cms.dao.ArticleContentMapper;
import com.xzjie.et.cms.dao.ArticleMapper;
import com.xzjie.et.cms.model.Article;
import com.xzjie.et.cms.model.ArticleContent;
import com.xzjie.et.cms.service.ArticleService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xzjie on 2017/8/29.
 */
@Service("articleService")
public class ArticleServiceImpl extends AbstractBaseService<Article, Long> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Override
    protected BaseMapper<Article, Long> getMapper() {
        return articleMapper;
    }

    @Override
    public PageEntity<Article> getArticlePage(Long categoryId, int currentPage) {
        PageEntity<Article> pageEntity = new PageEntity<Article>();
        Page page = new Page();
        Article article = new Article();
        article.setCategoryId(categoryId);
        page.setCurrentPage(currentPage);

        pageEntity.setT(article);
        pageEntity.setPage(page);

        return super.getListPage(pageEntity);
    }

    @Override
    public ArticleContent getArticleContent(Long articleId) {
        return articleContentMapper.selectArticleContent(articleId);
    }

    /**
     * @param categoryId
     * @param type       null 全部，0 不推荐，1 推荐
     * @return
     */
    @Override
    public List<Article> getRecommendList(Long categoryId, Integer type) {
        Article article = new Article();
        article.setCategoryId(categoryId);
        article.setRecommendType(type);
        return articleMapper.selectRecommendList(article);
    }

    @Override
    public List<Article> getArticleRecommendList(Long siteId, Long categoryId, int showCount) {
        PageEntity<Article> pageEntity = new PageEntity<Article>();
        Page page = new Page();
        Article article = new Article();
        article.setCategoryId(categoryId);
        article.setRecommendType(1);
        article.setSiteId(siteId);

        page.setCurrentPage(1);
        page.setShowCount(showCount);

        pageEntity.setT(article);
        pageEntity.setPage(page);

        List<Article> list = articleMapper.selectArticleListPage(pageEntity);
        return list;
    }

    @Override
    public PageEntity<Article> getArticleListPage(Long categoryId, int currentPage, int showCount) {
        PageEntity<Article> pageEntity = new PageEntity<Article>();
        Page page = new Page();
        Article article = new Article();
        article.setCategoryId(categoryId);
        page.setCurrentPage(currentPage);
        page.setShowCount(showCount);

        pageEntity.setT(article);
        pageEntity.setPage(page);

        List<Article> list = articleMapper.selectArticleListPage(pageEntity);
        pageEntity.setRows(list);
        return pageEntity;
    }

    @Override
    public PageEntity<Article> getArticleListPage(Long siteId, Long categoryId, Integer type, int currentPage, int showCount) {
        PageEntity<Article> pageEntity = new PageEntity<Article>();
        Page page = new Page();
        Article article = new Article();

        article.setSiteId(siteId);
        article.setCategoryId(categoryId);
        article.setRecommendType(type);

        page.setCurrentPage(currentPage);
        page.setShowCount(showCount);

        pageEntity.setT(article);
        pageEntity.setPage(page);

        List<Article> list = articleMapper.selectArticleListPage(pageEntity);
        pageEntity.setRows(list);
        return pageEntity;
    }

    @Override
    public List<Article> getArticleList(Long siteId, Long categoryId, Integer type, int showCount) {
        PageEntity<Article> pageEntity = this.getArticleListPage(siteId, categoryId, type, 1, showCount);
        return pageEntity.getRows();
    }

    @Override
    public List<Article> getArticleLatestListPage(Long siteId, int showCount) {
        PageEntity<Article> pageEntity = new PageEntity<Article>();
        Page page = new Page();
        Article article = new Article();

        page.setCurrentPage(1);
        page.setShowCount(showCount);

        article.setSiteId(siteId);

        pageEntity.setT(article);
        pageEntity.setPage(page);

        List<Article> list = articleMapper.selectListPage(pageEntity);
        return list;
    }

    @Override
    public boolean save(Article model) {
        articleMapper.insertSelective(model);
        ArticleContent content = new ArticleContent();
        content.setContent(model.getContent());
        content.setArticleId(model.getArticleId());
        articleContentMapper.insertSelective(content);
        return true;
    }

    @Override
    public boolean update(Article model) {
        ArticleContent content = new ArticleContent();
        content.setContent(model.getContent());
        content.setArticleId(model.getArticleId());
        articleMapper.updateByPrimaryKeySelective(model);
        int row = articleContentMapper.updateByArticleId(content);
        if (row < 1) {
            articleContentMapper.insertSelective(content);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Article article = new Article();
        article.setArticleId(id);
        article.setStatus(0);
        return this.update(article);
    }
}
