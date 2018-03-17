package com.xzjie.client.cms.web.tags;

import java.io.IOException;
import java.util.Map;

import com.xzjie.common.utils.SpringUtils;
import com.xzjie.et.cms.service.ArticleService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ArticleRecommendTags extends com.xzjie.common.web.freemarker.AbstractTemplateDirectiveModel {
    private ArticleService articleService = SpringUtils.getBean(ArticleService.class);
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
//        String id = params.getOrDefault("id", "").toString();
//        String categoryId =params.getOrDefault("categoryId", "").toString();
//        String showCount=params.getOrDefault("showCount", "").toString();
//        String name = params.getOrDefault("alias", "channel").toString();

       // List<Article> list = articleService.getArticleRecommendList(id,categoryId,showCount);
    }
}
