package com.xzjie.cms.ad.model;

import com.xzjie.cms.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_ad_position")
@ApiModel("广告位")
public class AdPosition extends BaseEntity<AdPosition> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("广告位名称")
    private String positionName;
    @ApiModelProperty("广告位code")
    private String positionCode;
    @ApiModelProperty("宽")
    private Short adWidth;
    @ApiModelProperty("高")
    private Short adHeight;
    @ApiModelProperty("类型")
    private String positionModel;
    @ApiModelProperty("说明")
    private String positionDesc;
    @ApiModelProperty("是否使用")
    private Boolean enabled;
    @ApiModelProperty("主题")
    private String theme;
    @ApiModelProperty("创建时间")
    @CreationTimestamp
    private Date createDate;

    @ApiModelProperty(value = "自定义",hidden = true)
    @Column(name = "position_style", columnDefinition = "text")
    private String positionStyle;

    public String getLabel() {
        return positionName;
    }

    @Override
    public void copy(AdPosition obj) {
        this.copyProperties(obj, this);
    }
}
