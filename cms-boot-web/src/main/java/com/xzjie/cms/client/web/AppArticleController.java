package com.xzjie.cms.client.web;

import com.xzjie.cms.client.dto.SearchDto;
import com.xzjie.cms.client.vo.ArticleDetailVo;
import com.xzjie.cms.client.vo.CaseVo;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.ArticleDto;
import com.xzjie.cms.dto.ArticleHotResult;
import com.xzjie.cms.dto.ArticleQueryDto;
import com.xzjie.cms.dto.BasePageDto;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.Category;
import com.xzjie.cms.service.ArticleService;
import com.xzjie.cms.service.LabelService;
import com.xzjie.cms.vo.LabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/article")
public class AppArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private LabelService labelService;

    @GetMapping(value = "/search")
    public Map<String, Object> getSearch(SearchDto query) {
        Page<Article> resultPage = articleService.getArticle(query);
        return MapUtils.success(resultPage.getContent(), resultPage.getTotalElements());
    }

    @GetMapping(value = "/hot")
    public Map<String, Object> getArticleHot(BasePageDto pageRequest) {
        Page<ArticleHotResult> hotResultPage = articleService.getArticleHot(pageRequest.getPage(), pageRequest.getSize());
        return MapUtils.success(hotResultPage.getContent(), hotResultPage.getTotalElements());
    }

    @GetMapping(value = "/recommend-stat")
    public Map<String, Object> recommendStat(ArticleQueryDto query) {
        query.setRecommendStat(1);
        Page<Article> recommendsPage = articleService.getArticle(query);
        List<Article> articles = recommendsPage.getContent();
        if (articles.size() < 1) {
            return MapUtils.success();
        }
        Article article = articles.get(0);

        return MapUtils.success().set("lead", article).set("articles", articles.subList(1, articles.size()));
    }

    @GetMapping(value = "/detail/{id}")
    public Map<String, Object> articleDetail(@PathVariable Long id) {
        ArticleDetailVo article = articleService.getArticleDetail(id);
        List<LabelVo> list = labelService.getLabelCounterList();
        return MapUtils.success(article).set("labels", list);
    }

    @PostMapping("/praise/{id}")
    public Map<String, Object> praise(@PathVariable Long id) {
        articleService.updatePraise(id);
        return MapUtils.success();
    }

    @GetMapping(value = "/category/{id}")
    public Map<String, Object> category(@PathVariable Long id, ArticleQueryDto query) {
        Map<String, Object> model = new HashMap<>();
        Category category = articleService.getCategory(id);
        query.setCategoryId(id);
        Page<Article> articlePage = articleService.getArticle(query);

        model.put("total", articlePage.getTotalElements());
        model.put("articles", articlePage.getContent());
        model.put("category", category);

        return MapUtils.success(model);
    }

    @GetMapping(value = "/cases/{id}")
    public Map<String, Object> cases(@PathVariable Long id, ArticleQueryDto query) {
        List<CaseVo> categories = articleService.getCaseData(id, query);
        return MapUtils.success(categories);
    }
}
