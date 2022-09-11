package com.xzjie.cms.dto;

import com.xzjie.cms.model.AdPosition;
import com.xzjie.cms.persistence.enums.ConditionType;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class AdPositionDto extends BasePageDto {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("广告位名称")
    @QueryCondition(connect = ConditionType.LIKE)
    private String positionName;
    @ApiModelProperty("广告位code")
    @QueryCondition
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
    @QueryCondition
    private Boolean enabled;
    @ApiModelProperty("主题")
    private String theme;
    @ApiModelProperty("创建时间")
    private Date createDate;
    @ApiModelProperty("自定义")
    private String positionStyle;

    public AdPosition toAdPosition() {
        AdPosition adPosition = new AdPosition();
        BeanUtils.copyProperties(this, adPosition);
        return adPosition;
    }
}
