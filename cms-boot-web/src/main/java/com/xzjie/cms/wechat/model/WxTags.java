package com.xzjie.cms.wechat.model;

import com.xzjie.cms.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
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
