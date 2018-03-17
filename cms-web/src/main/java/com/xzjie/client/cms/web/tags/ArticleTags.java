package com.xzjie.client.cms.web.tags;

import com.xzjie.common.utils.SpringUtils;
import com.xzjie.common.web.freemarker.AbstractTemplateDirectiveModel;
import com.xzjie.et.cms.model.Article;
import com.xzjie.et.cms.service.ArticleService;
import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ArticleTags extends AbstractTemplateDirectiveModel {
    private final Logger LOG = LogManager.getLogger(getClass());
    private final static String DICT_CODE = "ARTICLE_CATEGORY_ID";

    private ArticleService articleService = SpringUtils.getBean(ArticleService.class);


    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        SimpleScalar id = (SimpleScalar) params.get("id");
        SimpleScalar categoryId = (SimpleScalar) params.get("categoryId");
        SimpleScalar type = (SimpleScalar) params.get("type");
        SimpleScalar showCountParms = (SimpleScalar) params.get("showCount");
        Integer showCount = showCountParms == null ? 10 : Integer.parseInt(showCountParms.toString());
        SimpleScalar nameParms = (SimpleScalar)params.get("alias");
        String name = nameParms == null ? "article" : nameParms.toString();

        List<Article> list = null;
        try {
            Long catId = null;
            Integer _type = null;
            if (categoryId != null) {
                catId = Long.parseLong(categoryId.toString());
            }
            if (type != null) {
                _type = Integer.parseInt(type.toString());
            }
            list = articleService.getArticleList(Long.parseLong(id.toString()), catId, _type, showCount);

        } catch (Exception e) {
        } finally {
            env.setVariable(name, wrap(list));
            if (body != null) {
                body.render(env.getOut());
            }
        }

    }
}
