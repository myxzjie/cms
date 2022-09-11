package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.persistence.enums.ConditionType;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@ApiModel("广告Model")
public class AdDto extends BasePageDto {

    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("广告位ID")
    private Long positionId;
    @ApiModelProperty("类型")
    private Byte mediaType;

    @ApiModelProperty("名称")
    @QueryCondition(connect = ConditionType.LIKE)
    private String adName;
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("URL连接")
    private String adLink;
    @ApiModelProperty("广告CODE")
    @QueryCondition
    private String adCode;
    @ApiModelProperty("开始时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
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
    private Integer clickCount;
    @ApiModelProperty("是否使用")
    @QueryCondition
    private Boolean enabled;
    @ApiModelProperty("创建时间")
    private Date createDate;
    @ApiModelProperty("广告名称")
    private String positionName;

    public Ad toAd() {
        Ad ad = new Ad();
        BeanUtils.copyProperties(this, ad);
        return ad;
    }
}
