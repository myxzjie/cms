package com.xzjie.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Vito
 * @since 2022/3/18 10:58 下午
 */
@Data
@Entity
@Table(name = "sys_visit_log")
public class VisitLog extends BaseEntity<VisitLog> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String ip;
    private String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern="yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime pvDate;
    private String fromName;
    private String fromPath;
    private String fromQuery;
    private String toName;
    private String toPath;
    private String toQuery;

    @Override
    public void copy(VisitLog obj) {
        this.copyProperties(obj, this);
    }
}
