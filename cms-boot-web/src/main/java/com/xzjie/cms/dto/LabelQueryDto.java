package com.xzjie.cms.dto;

import com.xzjie.cms.persistence.annotation.QueryCondition;
import com.xzjie.cms.persistence.enums.ConditionType;
import lombok.Data;

@Data
public class LabelQueryDto extends BasePageDto {
    @QueryCondition(connect = ConditionType.LIKE)
    private String name;
}
