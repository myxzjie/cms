package com.xzjie.cms.picture.model;


import com.xzjie.cms.core.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
//@DynamicUpdate
@Where(clause = "state = 1")
@Table(name = "sys_pictures_group")
@SQLDelete(sql = "update sys_pictures_group set state = 0 where id = ?")
public class PicturesGroup extends BaseEntity<PicturesGroup> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String name;

    @Column(name = "state")
    private Integer state;

    @Override
    public void copy(PicturesGroup picturesGroup) {
        BeanUtils.copyProperties(picturesGroup, this, getIgnoreProperty(picturesGroup));
    }
}
