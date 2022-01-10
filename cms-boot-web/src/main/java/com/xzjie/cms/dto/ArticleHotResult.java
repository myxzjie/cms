package com.xzjie.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Vito
 * @since 2022/1/10 3:24 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleHotResult implements Serializable {
    private Long id;
    private Long articleId;
    private String title;
    private Integer sort;
    private LocalDateTime createDate;
}
