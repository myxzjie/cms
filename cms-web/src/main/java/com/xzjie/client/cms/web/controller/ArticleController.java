package com.xzjie.client.cms.web.controller;

import com.xzjie.et.cms.model.Article;
import com.xzjie.et.cms.model.ArticleContent;
import com.xzjie.et.cms.service.ArticleService;
import com.xzjie.et.cms.service.CategoryService;
import com.xzjie.common.web.utils.MapResult;
import com.xzjie.client.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by asus on 2017/8/29.
 */
@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value="{id}")
    public String index(@PathVariable Long id, Map<String, Object> modelView){

//        PageEntity<Article> pageEntity = articleService.getArticlePage(id,1);
        Article article = articleService.get(id);

        //Category category=categoryService.get(article.getCategoryId());
        modelView.put("categoryId",article.getCategoryId());
//        modelView.put("page",pageEntity.getPage());

        modelView.put("article",article);
        return getRemoteView("article");
    }

    @RequestMapping(value="content/{id}")
    @ResponseBody
    public Map<String, Object> articleContent(@PathVariable Long id, Map<String, Object> modelView){
        ArticleContent content = articleService.getArticleContent(id);
        return MapResult.mapOK(content,"0");
    }
}
