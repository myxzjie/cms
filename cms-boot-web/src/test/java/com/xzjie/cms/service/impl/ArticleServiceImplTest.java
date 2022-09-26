package com.xzjie.cms.service.impl;

import com.xzjie.cms.article.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Vito
 * @since 2022/7/20 4:09 下午
 */
@SpringBootTest
class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void getArticleByLabels() {
//        articleService.getArticleByLabels(Arrays.asList(1L),0,10);
    }
}