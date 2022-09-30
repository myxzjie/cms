package com.xzjie.cms.label.dto;

import com.xzjie.cms.core.persistence.annotation.QueryCondition;
import com.xzjie.cms.core.persistence.enums.ConditionType;
import com.xzjie.cms.dto.BasePageDto;
import lombok.Data;

@Data
public class LabelQueryDto extends BasePageDto {
    @QueryCondition(connect = ConditionType.LIKE)
    private String name;
}
