package com.xzjie.cms.article.vo;

import com.xzjie.cms.article.model.Article;
import com.xzjie.cms.article.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author vito(xiaozijie)
 * @since 2023/8/17 12:55 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCateVo {
    private Category category;
    private List<Article> articles;
    private Long total;
}
