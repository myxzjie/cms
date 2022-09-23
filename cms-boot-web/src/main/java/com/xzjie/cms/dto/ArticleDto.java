package com.xzjie.cms.dto;

import com.xzjie.cms.enums.Sorting;
import com.xzjie.cms.article.model.Article;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ArticleDto extends BasePageDto {

    @NotBlank(message = "标题不能为空")
    private String title;
    @NotNull(message = "类型不能为空")
    private Long categoryId;

    private String author;

    private String image;

    private String keywords;

    private String description;

    private Integer recommendStat;

    private Integer showState;
    @NotNull(message = "内容不能为空")
    private String content;

    private Sorting sorting;
    @NotNull(message = "标签不能为空")
    private List<Long> labels;

    public Article toArticle() {
        Article article = new Article();
        BeanUtils.copyProperties(this, article);
        return article;
    }
}
