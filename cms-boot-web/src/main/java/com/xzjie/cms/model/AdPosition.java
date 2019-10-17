package com.xzjie.cms.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_ad_position")
public class AdPosition {
    @Id
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

    @Column(name = "position_style", columnDefinition = "text")
    private String positionStyle;


}
