package com.xzjie.cms.client.web;

import com.xzjie.cms.client.dto.ArticleDetailResponse;
import com.xzjie.cms.client.dto.CaseResponse;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.ArticleHotResult;
import com.xzjie.cms.dto.ArticleRequest;
import com.xzjie.cms.dto.BasePageDto;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.Category;
import com.xzjie.cms.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/article")
public class AppArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/hot")
    public Map<String, Object> getArticleHot(BasePageDto pageRequest) {
        Page<ArticleHotResult> hotResultPage = articleService.getArticleHot(pageRequest.getPage(), pageRequest.getSize());
        return MapUtils.success(hotResultPage.getContent(), hotResultPage.getTotalElements());
    }

    @GetMapping(value = "/recommend-stat")
    public Map<String, Object> recommendStat(ArticleRequest articleRequest) {
        Article query = articleRequest.toArticle();
        query.setRecommendStat(1);
        Page<Article> recommendsPage = articleService.getArticle(articleRequest.getPage(), articleRequest.getSize(), query);
        List<Article> articles = recommendsPage.getContent();
        if (articles.size() < 1) {
            return MapUtils.success();
        }
        Article article = articles.get(0);

        return MapUtils.success().set("lead", article).set("articles", articles.subList(1, articles.size()));
    }

    @GetMapping(value = "/detail/{id}")
    public Map<String, Object> articleDetail(@PathVariable Long id) {
        ArticleDetailResponse article = articleService.getArticleDetail(id);
        return MapUtils.success(article).set("labels", new String[]{"设计师", "原则"});
    }

    @GetMapping(value = "/category/{id}")
    public Map<String, Object> category(@PathVariable Long id, ArticleRequest articleRequest) {
        Map<String, Object> model = new HashMap<>();
        Category category = articleService.getCategory(id);
        Article article = articleRequest.toArticle();
        article.setCategoryId(id);
        Page<Article> articlePage = articleService.getArticle(articleRequest.getPage(), articleRequest.getSize(), article);

        model.put("total", articlePage.getTotalElements());
        model.put("articles", articlePage.getContent());
        model.put("category", category);

        return MapUtils.success(model);
    }

    @GetMapping(value = "/cases/{id}")
    public Map<String, Object> cases(@PathVariable Long id, ArticleRequest articleRequest) {
        Article article = articleRequest.toArticle();
        List<CaseResponse> categories = articleService.getCaseData(id, article, articleRequest.getPage(), articleRequest.getSize());
        return MapUtils.success(categories);
    }
}
