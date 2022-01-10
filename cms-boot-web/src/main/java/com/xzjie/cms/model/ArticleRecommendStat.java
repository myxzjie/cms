package com.xzjie.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "cms_article_recommend")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArticleRecommendStat extends BaseEntity<ArticleRecommendStat> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private Integer sort;

    @CreationTimestamp
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Override
    public void copy(ArticleRecommendStat article) {
        BeanUtils.copyProperties(article, this, getIgnoreProperty(article));
    }
}
