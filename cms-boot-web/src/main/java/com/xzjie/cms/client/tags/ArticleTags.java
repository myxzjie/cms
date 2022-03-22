package com.xzjie.cms.client.tags;

import com.xzjie.cms.client.freemarker.AbstractTemplateModel;
import com.xzjie.cms.client.freemarker.annotation.FreemarkerComponent;
import com.xzjie.cms.dto.ArticleQueryDto;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.service.ArticleService;
import freemarker.core.Environment;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@FreemarkerComponent("article")
public class ArticleTags extends AbstractTemplateModel {
    private final static Integer CATEGORY_ID = 2;

    @Autowired
    private ArticleService articleService;


    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
//        SimpleScalar id = (SimpleScalar) params.get("id");
        Long categoryId = ((SimpleNumber) params.getOrDefault("categoryId", new SimpleNumber(CATEGORY_ID))).getAsNumber().longValue();
        Integer page = ((SimpleNumber) params.getOrDefault("page", new SimpleNumber(0))).getAsNumber().intValue();
        Integer size = ((SimpleNumber) params.getOrDefault("size", new SimpleNumber(10))).getAsNumber().intValue();
        String alias = ((SimpleScalar) params.getOrDefault("alias", new SimpleScalar("articles"))).getAsString();

        List<Article> list = null;
        try {
            ArticleQueryDto query = new ArticleQueryDto();
            query.setCategoryId(categoryId);
            query.setPage(page);
            query.setSize(size);
            Page<Article> articlePage = articleService.getArticle(query);
            list = articlePage.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Article tags error.", e);
        } finally {
            env.setVariable(alias, wrap(list));
            if (body != null) {
                body.render(env.getOut());
            }
        }

    }
}
