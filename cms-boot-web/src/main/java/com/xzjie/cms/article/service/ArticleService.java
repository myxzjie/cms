package com.xzjie.cms.article.service;

import com.xzjie.cms.article.dto.SearchDto;
import com.xzjie.cms.article.vo.CaseVo;
import com.xzjie.cms.article.vo.ArticleDetailVo;
import com.xzjie.cms.core.PageResult;
import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.article.dto.ArticleHotResult;
import com.xzjie.cms.article.dto.ArticleQueryDto;
import com.xzjie.cms.article.dto.ArticleRecommendStatResult;
import com.xzjie.cms.article.dto.CategoryTree;
import com.xzjie.cms.article.model.Article;
import com.xzjie.cms.article.model.ArticleHot;
import com.xzjie.cms.article.model.ArticleRecommendStat;
import com.xzjie.cms.article.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;


public interface ArticleService extends BaseService<Article> {

    Article getArticle(Long id);

    boolean updatePraise(Long id);

    ArticleDetailVo getArticleDetail(Long id);

    Page<Article> getArticle(SearchDto dto);

    Page<Article> getArticle(ArticleQueryDto dto);

    Page<Article> getArticleByLabels(ArticleQueryDto query);

    Category getCategoryFather(Long id);
    List<Category> getCateFather(Long id);

    Category getCategory(Long id);

    List<Category> getCategoriesById(Long pid);

    List<CaseVo> getCaseData(Long categoryId, ArticleQueryDto query);

    List<Category> getCategory();

    Page<Category> getCategory(Integer page, int size, Category category);

    List<CategoryTree> getCategoryTree();

    boolean saveCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(Long id);

    PageResult<ArticleHotResult> getArticleHot(Integer page, int size);


    boolean saveArticleHot(Set<Long> ids);

    boolean updateArticleHot(ArticleHot articleHot);

    boolean deleteArticleHot(Set<Long> ids);

    Page<ArticleRecommendStatResult> getRecommendStat(Integer page, int size);

    boolean saveRecommendStat(Set<Long> ids);

    boolean updateRecommendStat(ArticleRecommendStat recommendStat);

    boolean deleteRecommendStat(Set<Long> ids);

    List<Category> getCateVisById(Long id);

}
