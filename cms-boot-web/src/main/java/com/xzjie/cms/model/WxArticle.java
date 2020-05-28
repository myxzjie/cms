package com.xzjie.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "wx_article")
public class WxArticle extends BaseEntity<WxArticle> {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 作者
     */
    private String author;

    /**
     * 是否展示封面图片（0/1）
     */
    @Column(name = "is_cover", columnDefinition = "char")
    private String showCoverPic;

    /**
     * 上传微信，封面图片标识
     */
    @Column(name = "thumb_media_id")
    private String thumbMediaId;

    /**
     * 内容
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 内容链接
     */
    @Column(name = "url")
    private String contentSourceUrl;

    /**
     * 文章排序
     */
    @Column(name = "sort")
    private Integer orderNo;

    /**
     * 图片路径
     */
    @Column(name = "image")
    private String image;

    /**
     * 图文ID
     */
    @Column(name = "news_id")
    private String newsId;

    @CreationTimestamp
    private LocalDateTime createDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column(name = "is_comment")
    private String needOpenComment;

    @Column(name = "is_fans_comment")
    private String onlyFansCanComment;

    @Override
    public void copy(WxArticle obj) {
        copyProperties(obj, this);
    }
}
