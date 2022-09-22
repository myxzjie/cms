package com.xzjie.cms.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "wx_tags")
public class WxTags extends BaseEntity<WxTags> {
//    @Id
//    private Long id;

    private String name;

    private Integer count;

    @Override
    public void copy(WxTags obj) {
        copyProperties(obj, this);
    }
}
