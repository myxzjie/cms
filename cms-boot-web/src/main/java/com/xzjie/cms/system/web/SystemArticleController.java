package com.xzjie.cms.system.web;

import com.xzjie.cms.client.web.BaseController;
import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.*;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.ArticleHot;
import com.xzjie.cms.model.ArticleRecommendStat;
import com.xzjie.cms.model.Category;
import com.xzjie.cms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping({"/api/article", "/api/system/article"})
public class SystemArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/create")
    public Map<String, Object> create(@Valid @RequestBody ArticleRequest model) {
        Article article = model.toArticle();
        article.setCreateDate(LocalDateTime.now());
        articleService.save(article);

        return MapUtils.success();
    }

    @PutMapping(value = "/update/{id}")
    public Map<String, Object> update(@PathVariable Long id, @Valid @RequestBody ArticleRequest model) {
        Article article = model.toArticle();
        article.setId(id);

        articleService.update(article);

        return MapUtils.success();
    }

    @PutMapping(value = "/update/recommend-stat/{id}/{recommendStat}")
    public Map<String, Object> update(@PathVariable Long id, @PathVariable Integer recommendStat) {
        Article article = new Article();
        article.setId(id);
        article.setRecommendStat(recommendStat);

        articleService.update(article);
        return MapUtils.success();
    }

    @DeleteMapping(value = "/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        articleService.delete(id);
        return MapUtils.success();
    }

    @Log(value = "article_list", descrption = "the article list")
    @GetMapping(value = "/list")
//    @PreAuthorize("hasAuthority('user')")
    public Map<String, Object> articleList(ArticleRequest article) {
        Page<Article> articlePage = articleService.getArticle(article.getPage(), article.getSize(), article.toArticle());
        return MapUtils.success(articlePage.getContent(), articlePage.getTotalElements());
    }

    @GetMapping(value = "/{id}")
    public String article(@PathVariable Long id, Map<String, Object> modelView) {

//        PageEntity<Article> pageEntity = articleService.getArticlePage(id,1);
        Article article = articleService.getArticle(id);

        //Category category=categoryService.get(article.getCategoryId());
        modelView.put("categoryId", article.getCategoryId());
//        modelView.put("page",pageEntity.getPage());

        modelView.put("article", article);
        return getRemoteView("article-details");
    }


    @GetMapping(value = "/recommend-stat")
    public Map<String, Object> getRecommendStat(BasePageDto page) {
        Page<ArticleRecommendStatResult> resultPage = articleService.getRecommendStat(page.getPage(), page.getSize());
        return MapUtils.success(resultPage.getContent(), resultPage.getTotalElements());
    }

    @PostMapping(value = "/recommend-stat")
    public Map<String, Object> createRecommendStat(@RequestBody Set<Long> ids) {
        articleService.saveRecommendStat(ids);
        return MapUtils.success();
    }

    @PutMapping(value = "/recommend-stat")
    public Map<String, Object> createArticleHot(@RequestBody ArticleRecommendStat recommendStat) {
        articleService.updateRecommendStat(recommendStat);
        return MapUtils.success();
    }

    @DeleteMapping(value = "/recommend-stat")
    public Map<String, Object> deleteRecommendStat(@RequestBody Set<Long> ids) {
        articleService.deleteRecommendStat(ids);
        return MapUtils.success();
    }

    @PostMapping(value = "/hot")
    public Map<String, Object> createArticleHot(@RequestBody Set<Long> ids) {
        articleService.saveArticleHot(ids);
        return MapUtils.success();
    }

    @PutMapping(value = "/hot")
    public Map<String, Object> createArticleHot(@RequestBody ArticleHot articleHot) {
        articleService.updateArticleHot(articleHot);
        return MapUtils.success();
    }

    @DeleteMapping(value = "/hot")
    public Map<String, Object> deleteArticleHot(@RequestBody Set<Long> ids) {
        articleService.deleteArticleHot(ids);
        return MapUtils.success();
    }

    @GetMapping(value = "/hot")
    public Map<String, Object> getArticleHot(BasePageDto page) {
        Page<ArticleHotResult> resultPage = articleService.getArticleHot(page.getPage(), page.getSize());
        return MapUtils.success(resultPage.getContent(), resultPage.getTotalElements());
    }

    @GetMapping(value = "/category")
    public Map<String, Object> category(CategoryRequest category) {
        Page<Category> categoryPage = articleService.getCategory(category.getPage(), category.getSize(), category.toCategory());

        return MapUtils.success(categoryPage.getContent(), categoryPage.getTotalElements());
    }

    @PostMapping(value = "/category/create")
    public Map<String, Object> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequest.toCategory();
        articleService.saveCategory(category);
        return MapUtils.success();
    }

    @PutMapping(value = "/category/update/{id}")
    public Map<String, Object> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequest.toCategory();
        category.setId(id);
        articleService.updateCategory(category);

        return MapUtils.success();
    }

    @DeleteMapping(value = "/category/delete/{id}")
    public Map<String, Object> deleteCategory(@PathVariable Long id) {
        articleService.deleteCategory(id);

        return MapUtils.success();
    }
}
