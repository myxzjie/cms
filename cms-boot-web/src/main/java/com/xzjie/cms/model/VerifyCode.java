package com.xzjie.cms.model;

import com.xzjie.cms.enums.VerifyCodeScenes;
import com.xzjie.cms.enums.VerifyCodeType;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Where(clause = "state = 1")
@Entity(name = "sys_verify_code")
public class VerifyCode extends BaseEntity<VerifyCode> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scenes", columnDefinition = "varchar")
    private VerifyCodeScenes scenes;
    private String value;
    private String target;

    @Column(name = "type", columnDefinition = "varchar")
    private VerifyCodeType type;
    private LocalDateTime createDate;
    private String message;
    private Integer state;

    @Override
    public void copy(VerifyCode obj) {
        copyProperties(obj, this);
    }
}
