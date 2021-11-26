package com.xzjie.cms.client.web;

import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.Category;
import com.xzjie.cms.service.ArticleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = {"", "/", "index"})
    public String index(Map<String, Object> model) {
        Article query = new Article();
        Page<Article> articlePage = articleService.getArticle(0, 2, query);

        query.setRecommendStat(1);
        Page<Article> recommendsPage = articleService.getArticle(0, 5, query);
        model.put("articleLatest", articlePage.getContent());
        model.put("recommends", recommendsPage.getContent());
        return getRemoteView("article");
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

    @GetMapping(value = "/category/{id}")
    public String category(@PathVariable Long id, Map<String, Object> model) {
        Article article = new Article();
        article.setCategoryId(id);
        Page<Article> articlePage = articleService.getArticle(0, 10, article);
        Category category = articleService.getCategory(id);
        article.setRecommendStat(1);
        Page<Article> recommendsPage = articleService.getArticle(0, 5, article);
        model.put("categoryId", id);
        model.put("page", articlePage.getPageable());
        model.put("articles", articlePage.getContent());
        model.put("category", category);
        model.put("recommends", recommendsPage.getContent());
        //模板页面
        if (StringUtils.isNotBlank(category.getTemplate())) {
            return getRemoteView(category.getTemplate());
        }
        return getRemoteView("category");
    }
}
