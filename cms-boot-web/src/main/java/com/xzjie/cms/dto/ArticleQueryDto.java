package com.xzjie.cms.dto;

import com.xzjie.cms.core.persistence.annotation.QueryCondition;
import lombok.Data;

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
