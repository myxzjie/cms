package com.xzjie.cms.dto;

import com.xzjie.cms.model.AdPosition;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class AdPositionRequest extends BasePageRequest {

    private Long id;

    private Long userId;

    private String positionName;

    private String positionCode;

    private Short adWidth;

    private Short adHeight;

    private String positionModel;

    private String positionDesc;

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
