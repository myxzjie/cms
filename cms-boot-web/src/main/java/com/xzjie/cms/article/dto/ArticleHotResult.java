package com.xzjie.cms.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("内容id")
    private Long articleId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("摘要")
    private String description;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("创建时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createDate;

}
