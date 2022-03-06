package com.xzjie.cms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Vito
 * @since 2022/1/10 3:24 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleHotResult implements Serializable {
    private Long id;
    private Long articleId;
    private String title;
    private String description;
    private Integer sort;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate;

}
