package com.xzjie.cms.article.model;

import com.xzjie.cms.core.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cms_topic")
@Where(clause = "state = 1")
@SQLDelete(sql = "update cms_topic set state = 0 where id = ?")
public class Topic extends BaseEntity<Topic> {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String topic;

    private String description;

    private String coverUrl;

    private Integer sort;

    private Integer recommendStat;

    private Integer state;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @Override
    public void copy(Topic obj) {

    }
}
