package com.xzjie.cms.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_ad_position")
public class AdPosition extends BaseEntity<AdPosition> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @CreationTimestamp
    private Date createDate;

    @Column(name = "position_style", columnDefinition = "text")
    private String positionStyle;

    public String getLabel() {
        return positionName;
    }

    @Override
    public void copy(AdPosition obj) {
        this.copyProperties(obj, this);
    }
}
