package com.xzjie.client.cms.web.controller;

import com.alibaba.fastjson.JSON;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.cms.model.Article;
import com.xzjie.et.cms.model.Category;
import com.xzjie.et.cms.service.ArticleService;
import com.xzjie.et.cms.service.CategoryService;
import com.xzjie.client.core.web.BaseController;
import com.xzjie.mybatis.page.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/8/28.
 */
@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "{id}")
    public String index(@PathVariable Long id, Map<String, Object> model) {

        PageEntity<Article> pageEntity = articleService.getArticleListPage(id, 1, 10);
        Category category = categoryService.get(id);
        List<Article> list = articleService.getArticleRecommendList(getSiteId(), id, 5);
        model.put("categoryId", id);
        model.put("page", pageEntity.getPage());
        model.put("articles", pageEntity.getRows());
        model.put("category", category);
        model.put("recommends", list);
        //模板页面
        if (StringUtils.isNotBlank(category.getTemplate())) {
            return getRemoteView(category.getTemplate());
        }
        return getRemoteView("product-show");
    }

    @RequestMapping(value = "dataPage/{id}")
    public String dataPage(@PathVariable Long id, int currentPage, Map<String, Object> model) {

        PageEntity<Article> pageEntity = articleService.getArticleListPage(id, currentPage, 10);
        //Category category = categoryService.get(id);
        model.put("categoryId", id);
        model.put("page", pageEntity.getPage());
        model.put("articles", pageEntity.getRows());
        //model.put("category",category);
        //S//ystem.out.println(JSON.toJSONString(category));
        return getRemoteView("product-list");
    }

    @RequestMapping(value = "page/content")
    public String contentPage(Long categoryId, Integer type, int currentPage, Map<String, Object> model) {
        PageEntity<Article> pageEntity = articleService.getArticleListPage(getSiteId(), categoryId, type, currentPage, 10);
        model.put("categoryId", categoryId);
        model.put("page", pageEntity.getPage());
        model.put("article", pageEntity.getRows());

        return getRemoteView("content-list");
    }
}
