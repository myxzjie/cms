package com.xzjie.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "cms_article")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Article extends BaseEntity<Article> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    private String title;

    private String url;

    private String image;

    private String keywords;

    private String description;

    private String author;

    private Long userId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updateDate;

    private Integer countRead;

    private Integer countComment;

    private Integer sort;

    private Integer state;

    private Integer approveStatus;

    private Date publishDate;

    private Long publishAuthor;

    private Date startTime;

    private Date endTime;

    private String remarks;

    private Integer recommendStat;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    private Integer showState;

    @Transient
    private String authorName;

    @Transient
    private String categoryName;

    @Transient
    private String name;

    @Transient
    private String nickName;

    @Transient
    private String avatar;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name = "cms_article_label",joinColumns={@JoinColumn(name="articleId")},inverseJoinColumns={@JoinColumn(name="labelId")})
    private List<Label> labels;

    @Override
    public void copy(Article article) {
        BeanUtils.copyProperties(article, this, getIgnoreProperty(article));
    }
}
