package com.xzjie.cms.ad.vo;

import com.xzjie.cms.ad.model.Ad;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author vito
 * @date : 2022/9/22 11:21 AM
 */
@Data
public class AdVo extends Ad {
    @ApiModelProperty("广告位名称")
    private String positionCode;
    @ApiModelProperty("广告位名称")
    private String positionName;
}
