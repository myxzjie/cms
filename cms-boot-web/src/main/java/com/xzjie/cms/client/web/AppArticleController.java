package com.xzjie.cms.client.web;

import com.xzjie.cms.article.dto.SearchDto;
import com.xzjie.cms.article.vo.ArticleCateVo;
import com.xzjie.cms.article.vo.ArticleDetailVo;
import com.xzjie.cms.article.vo.CaseVo;
import com.xzjie.cms.article.vo.CategoriesVo;
import com.xzjie.cms.core.PageResult;
import com.xzjie.cms.core.Result;
import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.article.dto.ArticleHotResult;
import com.xzjie.cms.article.dto.ArticleQueryDto;
import com.xzjie.cms.dto.BasePageDto;
import com.xzjie.cms.article.model.Article;
import com.xzjie.cms.label.service.LabelService;
import com.xzjie.cms.article.model.Category;
import com.xzjie.cms.article.service.ArticleService;
import com.xzjie.cms.vo.LabelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "前端-内容管理", tags = "前端-内容管理")
@RestController
@RequestMapping("/app/article")
public class AppArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "内容搜索列表", notes = "内容搜索列表", response = Article.class)
    @GetMapping(value = "/search")
    public Result<List<Article>> getSearch(SearchDto query) {
        Page<Article> resultPage = articleService.getArticle(query);
        return Result.data(resultPage.getContent(), resultPage.getTotalElements());
    }

    @ApiOperation(value = "热门数据内容列表", notes = "热门数据内容列表", response = ArticleHotResult.class)
    @GetMapping(value = "/hot")
    public Result<List<ArticleHotResult>> getArticleHot(BasePageDto pageRequest) {
        PageResult<ArticleHotResult> result = articleService.getArticleHot(pageRequest.getPage(), pageRequest.getSize());
        return Result.data(result);
    }

    @ApiOperation(value = "推荐数据内容列表", notes = "推荐数据内容列表", response = ArticleHotResult.class)
    @GetMapping(value = "/recommend-stat")
    public Result<?> recommendStat(ArticleQueryDto query) {
        query.setRecommendStat(1);
        Page<Article> recommendsPage = articleService.getArticle(query);
        List<Article> articles = recommendsPage.getContent();
        if (articles.size() < 1) {
            return Result.success();
        }
//        Article article = articles.get(0);
//        Map<String, Object> map = new HashMap<>();
//        map.put("lead", article);
//        map.put("articles", articles.subList(1, articles.size()));
        return Result.data(articles);
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
    public Result<ArticleCateVo> category(@PathVariable Long id, ArticleQueryDto query) {
//        Map<String, Object> model = new HashMap<>();
        Category category = articleService.getCategory(id);
        query.setCategoryId(id);
        Page<Article> articlePage = articleService.getArticle(query);

        ArticleCateVo model = ArticleCateVo.builder()
                .category(category)
                .articles(articlePage.getContent())
                .total(articlePage.getTotalElements())
                .build();
//        model.put("total", articlePage.getTotalElements());
//        model.put("articles", articlePage.getContent());
//        model.put("category", category);

        return Result.data(model);
    }

    @GetMapping(value = "/cases/{id}")
    public Result<CategoriesVo> cases(@PathVariable Long id, ArticleQueryDto query) {
        Category category = articleService.getCategory(id);
        List<CaseVo> categories = articleService.getCaseData(id, query);
        return Result.data(CategoriesVo
                .builder()
                .cate(category)
                .categories(categories)
                .build());
    }

    @GetMapping(value = "/label")
    public Map<String, Object> getArticleByLabels(ArticleQueryDto query) {
        Page<Article> resultPage = articleService.getArticleByLabels(query);
        return MapUtils.success(resultPage.getContent(), resultPage.getTotalElements());
    }
}
