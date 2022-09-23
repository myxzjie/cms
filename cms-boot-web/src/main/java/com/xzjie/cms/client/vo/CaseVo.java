package com.xzjie.cms.client.vo;

import com.xzjie.cms.article.model.Article;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CaseVo {
    private Long id;

    private Long pid;

    private String categoryName;

    private String image;

    private String url;

    private String target;

    private String keywords;

    private String description;

    private Integer sort;

    private Integer state;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer showState;

    private List<Article> articles = new ArrayList<>();
}
