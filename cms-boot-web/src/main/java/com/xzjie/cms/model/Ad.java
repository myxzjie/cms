package com.xzjie.cms.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_ad")
public class Ad  {
    @Id
    private Long id;

    private Long positionId;

    private Byte mediaType;

    private String adName;

    private String adLink;

    private String adCode;

    private Date startTime;

    private Date endTime;

    private String man;

    private String email;

    private String phone;

    private Integer clickCount;

    private Boolean enabled;

    private Date createDate;

    @Transient
    private String positionName;
}
