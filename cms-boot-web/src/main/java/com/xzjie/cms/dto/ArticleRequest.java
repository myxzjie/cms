package com.xzjie.cms.dto;

import com.xzjie.cms.model.Article;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ArticleRequest extends BasePageRequest {

    @NotBlank
    private String title;
    @NotNull
    private Long categoryId;

    private String author;

    private String image;

    private String description;

    private Integer recommendStat;

    private Integer showState;

    private String content;



    public Article toArticle() {
        Article article = new Article();
        BeanUtils.copyProperties(this, article);
        return article;
    }
}
