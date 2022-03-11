package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.persistence.enums.ConditionType;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class AdRequest extends BasePageDto {

    private Long id;

    private Long positionId;

    private Byte mediaType;
    @QueryCondition(connect = ConditionType.LIKE)
    private String adName;

    private String image;

    private String adLink;
    @QueryCondition
    private String adCode;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String man;

    private String email;

    private String phone;

    private Integer clickCount;

    @QueryCondition
    private Boolean enabled;

    private Date createDate;

    private String positionName;


    public Ad toAd() {
        Ad ad = new Ad();
        BeanUtils.copyProperties(this, ad);
        return ad;
    }
}
