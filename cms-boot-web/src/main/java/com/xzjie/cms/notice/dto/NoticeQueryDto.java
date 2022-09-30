package com.xzjie.cms.notice.dto;

import com.xzjie.cms.core.persistence.annotation.QueryCondition;
import com.xzjie.cms.dto.BasePageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author vito
 * @date : 2022/9/23 3:10 PM
 */
@ApiModel("通知查询对象")
@Data
public class NoticeQueryDto extends BasePageDto {

    @ApiModelProperty("标题")
    @QueryCondition(blurry = {"title"})
    private String title;

}
