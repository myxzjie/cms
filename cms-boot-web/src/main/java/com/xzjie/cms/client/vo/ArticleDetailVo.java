package com.xzjie.cms.client.vo;

import com.xzjie.cms.article.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    private Article article;
    private Article prev;
    private Article next;

    public static ArticleDetailVo create(Article article, Article prev, Article next) {
        return new ArticleDetailVo(article, prev, next);
    }
}
