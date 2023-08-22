package com.xzjie.cms.article.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.xzjie.cms.core.entity.BaseEntity;
import com.xzjie.cms.label.model.Label;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "cms_article")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel("内容对象")
public class Article extends BaseEntity<Article> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ApiModelProperty("ID")
//    private Long id;
    @ApiModelProperty("类型ID")
    private Long categoryId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("URL连接")
    private String url;
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("关键字")
    private String keywords;
    @ApiModelProperty("说明")
    private String description;
    @ApiModelProperty("作者")
    private String author;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("创建时间")
    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate;
    @ApiModelProperty("修改时间")
    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updateDate;
    @ApiModelProperty("阅读量")
    private Integer countRead;
    @ApiModelProperty("评论数量")
    private Integer countComment;
    @ApiModelProperty("点击数量")
    private Integer countPraise;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("审核")
    private Integer approveStatus;
    @ApiModelProperty("审核时间")
    private Date publishDate;
    @ApiModelProperty("审核用户")
    private Long publishAuthor;
    @ApiModelProperty("开始时间")
    private Date startTime;
    @ApiModelProperty("结束时间")
    private Date endTime;
    @ApiModelProperty("备注")
    private String remarks;
    @ApiModelProperty("是否推荐")
    private Integer recommendStat;
    @ApiModelProperty("内容")
    @Column(name = "content", columnDefinition = "longtext")
    private String content;
    @ApiModelProperty("是否显示")
    private Integer showState;
    @ApiModelProperty("作者")
    @Transient
    private String authorName;
    @ApiModelProperty("类型名称")
    @Transient
    private String categoryName;
    @ApiModelProperty("用户名称")
    @Transient
    private String name;
    @ApiModelProperty("用户昵称")
    @Transient
    private String nickName;
    @ApiModelProperty("头像")
    @Transient
    private String avatar;
    @ApiModelProperty("标签")
    // cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cms_article_label", joinColumns = {@JoinColumn(name = "articleId")}, inverseJoinColumns = {@JoinColumn(name = "labelId")})
    private List<Label> labels;

    @Override
    public void copy(Article article) {
        BeanUtils.copyProperties(article, this, getIgnoreProperty(article));
    }
}
