package com.xzjie.cms.dto;

import com.xzjie.cms.enums.Sorting;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ArticleQueryDto extends BasePageDto {

    @QueryCondition(blurry = {"title", "keywords", "description"})
    private String title;
    @QueryCondition
    private Long categoryId;

    @QueryCondition
    private Integer recommendStat;

    private List<Long> labelIds;

}
