package com.xzjie.cms.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cms_article_label")
@Where(clause = "state = 1")
@SQLDelete(sql = "update cms_article_label set state = 0 where id = ?")
public class ArticleLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private Long labelId;
}
