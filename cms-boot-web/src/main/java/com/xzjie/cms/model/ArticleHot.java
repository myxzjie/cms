package com.xzjie.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "cms_article_hot")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArticleHot extends BaseEntity<ArticleHot> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private Integer sort;

    @CreationTimestamp
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Override
    public void copy(ArticleHot article) {
        BeanUtils.copyProperties(article, this, getIgnoreProperty(article));
    }
}
