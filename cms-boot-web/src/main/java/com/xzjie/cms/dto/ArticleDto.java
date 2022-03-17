package com.xzjie.cms.dto;

import com.xzjie.cms.enums.Sorting;
import com.xzjie.cms.model.Article;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ArticleDto extends BasePageDto {

    @NotBlank
    private String title;
    @NotNull
    private Long categoryId;

    private String author;

    private String image;

    private String keywords;

    private String description;

    private Integer recommendStat;

    private Integer showState;

    private String content;

    private Sorting sorting;

    private List<Long> labels;

    public Article toArticle() {
        Article article = new Article();
        BeanUtils.copyProperties(this, article);
        return article;
    }
}
