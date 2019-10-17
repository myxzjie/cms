package com.xzjie.cms.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sys_pictures")
@SQLDelete(sql = "update sys_pictures set state = 0 where id = ?")
@Where(clause = "state = 1")
public class Pictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;

    private Long userId;

    private String name;

    private String url;

    private Integer type;

    private LocalDateTime createDate;

    private Integer state;

    private String origin;
}
