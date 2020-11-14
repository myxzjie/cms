package com.xzjie.cms.client.dto;

import com.xzjie.cms.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailResponse {
    private Article article;
    private Article prev;
    private Article next;

    public static ArticleDetailResponse create(Article article, Article prev, Article next) {
        return new ArticleDetailResponse(article, prev, next);
    }
}
