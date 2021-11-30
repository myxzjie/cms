package com.xzjie.cms.service;

import com.xzjie.cms.client.dto.ArticleDetailResponse;
import com.xzjie.cms.client.dto.CaseResponse;
import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.dto.CategoryTree;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ArticleService extends BaseService<Article> {

    Article getArticle(Long id);

    ArticleDetailResponse getArticleDetail(Long id);

    Page<Article> getArticle(Integer page, int size, Article query);

    Category getCategory(Long id);

    List<CaseResponse> getCaseData(Long categoryId, Article article, Integer page, Integer size);

    List<Category> getCategory();

    Page<Category> getCategory(Integer page, int size, Category category);

    List<CategoryTree> getCategoryTree();

    boolean saveCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(Long id);


//
//    /**
//     * @param categoryId
//     * @param type       null 全部，0 不推荐，1 推荐
//     * @return
//     */
//    List<Article> getRecommendList(Long categoryId, Integer type);
//
//
//    List<Article> getArticleRecommendList(Long siteId, Long categoryId, int showCount);
//
//    /**
//     * 获得内容数据包括categoryId子类的内容
//     *
//     * @param categoryId
//     * @param currentPage
//     * @param showCount
//     * @return
//     */
//    PageEntity<Article> getArticleListPage(Long categoryId, int currentPage, int showCount);
//
//    /**
//     * @param siteId
//     * @param categoryId null 不分
//     * @param showCount
//     * @param type       null 全部，0 不推荐，1 推荐
//     * @return
//     */
//    PageEntity<Article> getArticleListPage(Long siteId, Long categoryId, Integer type, int currentPage, int showCount);
//
//    List<Article> getArticleList(Long siteId, Long categoryId, Integer type, int showCount);
//
//    /**
//     * 获得最新动态
//     *
//     * @param siteId
//     * @param showCount
//     * @return
//     */
//    List<Article> getArticleLatestListPage(Long siteId, int showCount);
}
