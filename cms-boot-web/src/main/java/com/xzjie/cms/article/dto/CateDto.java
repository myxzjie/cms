package com.xzjie.cms.article.dto;

import com.xzjie.cms.article.model.Category;
import com.xzjie.cms.dto.BasePageDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class CateDto extends BasePageDto {

    private Long pid;

    @NotBlank
    private String categoryName;

    private String image;

    private String url;

    private String target;

    private String keywords;

    private String description;

    private Integer sort;

    private Integer showState;

    public Category toCategory() {
        Category category = new Category();
        BeanUtils.copyProperties(this, category);
        return category;
    }
}
