package com.xzjie.cms.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wx_fans_tag")
public class WxFansTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fans_id")
    private Long fansId;
    @Column(name = "tag_id")
    private Long tagId;
    private String openId;

//    @OneToMany
//    @JoinColumn(name = "fans_Id", updatable = false, insertable = false)
//    private WxAccountFans fans;
}
