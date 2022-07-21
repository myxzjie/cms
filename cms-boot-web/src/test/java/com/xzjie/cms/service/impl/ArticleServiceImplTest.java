package com.xzjie.cms.service.impl;

import com.xzjie.cms.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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