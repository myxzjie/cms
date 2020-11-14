package com.xzjie.cms.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cms_label")
@Where(clause = "state = 1")
@SQLDelete(sql = "update cms_label set state = 0 where id = ?")
public class Label extends BaseEntity<Label> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer state;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Override
    public void copy(Label obj) {

    }
}
