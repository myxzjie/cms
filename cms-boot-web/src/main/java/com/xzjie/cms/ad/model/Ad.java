package com.xzjie.cms.ad.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzjie.cms.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_ad")
@ApiModel("广告数据")
public class Ad extends BaseEntity<Ad> {

    @ApiModelProperty("广告位ID")
    private Long positionId;
    @ApiModelProperty("类型")
    private Byte mediaType;
    @ApiModelProperty("名称")
    private String adName;
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("URL连接")
    private String adLink;
    @ApiModelProperty("内容")
    private String content;
    @ApiModelProperty("开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @ApiModelProperty("结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty("联系人")
    private String man;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("点击量")
    @Column(columnDefinition = "int default 0")
    private Integer clickCount;
    @ApiModelProperty("是否使用")
    private Boolean enabled;
    @ApiModelProperty("创建时间")
    @CreationTimestamp
    private Date createDate;

    @Override
    public void copy(Ad obj) {
        this.copyProperties(obj, this);
    }
}
