package com.xzjie.cms.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "wx_article_template")
public class WxArticleTemplate extends BaseEntity<WxArticleTemplate> {
    /**
     * 主键 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板名称
     */
    @Column(name = "tpl_name")
    private String templateName;

    /**
     * 是否已上传微信
     */
    @Column(name = "publish")
    private Boolean publish;

    @Column(name = "media_id")
    private String mediaId;


//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @CreationTimestamp
    private LocalDateTime createDate;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @UpdateTimestamp
    private Date updateDate;

    @Override
    public void copy(WxArticleTemplate obj) {
        copyProperties(obj, this);
    }

//    @Transient
//    private String wxAccountName;//公众号名称
//
//    @Transient
//    private Integer countArticle;//图文数量
//
//    @Transient
//    private String isRelatedMenu;//是否已关联菜单
//
//    //权限过滤使用
//    @Transient
//    private boolean filterRole;//不过滤权限默认false,过滤权限true
//    @Transient
//    private String wxAccountIds;//权限过滤使用
}
