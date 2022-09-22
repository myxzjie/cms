package com.xzjie.cms.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sys_key_data")
@NoArgsConstructor
public class KeyData extends BaseEntity<KeyData> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "`key`")
    private String key;

    @Column(name = "`data`", columnDefinition = "json")
    private String data;

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Override
    public void copy(KeyData obj) {
        copyProperties(obj, this);
    }
}
