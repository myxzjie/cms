package com.xzjie.cms.system.web;

import com.xzjie.cms.client.web.BaseController;
import com.xzjie.cms.core.annotation.Log;
import com.xzjie.cms.dto.ArticleRequest;
import com.xzjie.cms.dto.CategoryRequest;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.Category;
import com.xzjie.cms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class SystemArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/create")
    public Map<String, Object> create(@Valid @RequestBody ArticleRequest model) {
        Map<String, Object> map = new HashMap<>();
        Article article = model.toArticle();
        article.setCreateDate(LocalDateTime.now());
        articleService.save(article);

        map.put("code", 0);
        return map;
    }

    @PutMapping(value = "/update/{id}")
    public Map<String, Object> update(@PathVariable Long id, @Valid @RequestBody ArticleRequest model) {
        Map<String, Object> map = new HashMap<>();
        Article article = model.toArticle();
        article.setId(id);

        articleService.update(article);

        map.put("code", 0);
        return map;
    }

    @DeleteMapping(value = "/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();

        articleService.delete(id);

        map.put("code", 0);
        return map;
    }

    @Log(value = "article_list",descrption = "the article list")
    @GetMapping(value = "/list")
//    @PreAuthorize("hasAuthority('user')")
    public Map<String, Object> articleList(ArticleRequest article) {
        Map<String, Object> map = new HashMap<>();
        Page<Article> articlePage = articleService.getArticle(article.getPage(), article.getSize(), article.toArticle());
        map.put("data", articlePage.getContent());
        map.put("total", articlePage.getTotalElements());
        map.put("code", 0);
        return map;
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

    @GetMapping(value = "/category")
    public Map<String, Object> category(CategoryRequest category) {
        Map<String, Object> map = new HashMap<>();
        Page<Category> categoryPage = articleService.getCategory(category.getPage(), category.getSize(), category.toCategory());

        map.put("code", 0);
        map.put("data", categoryPage.getContent());
        map.put("total", categoryPage.getTotalPages());
        return map;
    }

    @PostMapping(value = "/category/create")
    public Map<String, Object> createCategory(@RequestBody CategoryRequest categoryRequest) {
        Map<String, Object> map = new HashMap<>();
        Category category = categoryRequest.toCategory();
        articleService.saveCategory(category);

        map.put("code", 0);
        return map;
    }

    @PutMapping(value = "/category/update/{id}")
    public Map<String, Object> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        Map<String, Object> map = new HashMap<>();
        Category category = categoryRequest.toCategory();
        category.setId(id);
        articleService.updateCategory(category);

        map.put("code", 0);
        return map;
    }

    @DeleteMapping(value = "/category/delete/{id}")
    public Map<String, Object> deleteCategory(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        articleService.deleteCategory(id);

        map.put("code", 0);
        return map;
    }
}
