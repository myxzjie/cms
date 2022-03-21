package com.xzjie.cms.dto;

import com.xzjie.cms.model.AdPosition;
import com.xzjie.cms.persistence.enums.ConditionType;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class AdPositionDto extends BasePageDto {

    private Long id;

    private Long userId;

    @QueryCondition(connect = ConditionType.LIKE)
    private String positionName;
    @QueryCondition
    private String positionCode;

    private Short adWidth;

    private Short adHeight;

    private String positionModel;

    private String positionDesc;

    @QueryCondition
    private Boolean enabled;

    private String theme;

    private Date createDate;

    private String positionStyle;


    public AdPosition toAdPosition() {
        AdPosition adPosition = new AdPosition();
        BeanUtils.copyProperties(this, adPosition);
        return adPosition;
    }
}
