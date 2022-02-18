package com.xzjie.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cms_ad")
@EntityListeners(AuditingEntityListener.class)
public class Ad extends BaseEntity<Ad> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long positionId;

    private Byte mediaType;

    private String adName;

    private String image;

    private String adLink;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String man;

    private String email;

    private String phone;

    @Column(columnDefinition = "int default 0")
    private Integer clickCount;

    private Boolean enabled;

    @CreationTimestamp
    private Date createDate;

    @Transient
    private String positionName;

    @Override
    public void copy(Ad obj) {
        this.copyProperties(obj, this);
    }
}
