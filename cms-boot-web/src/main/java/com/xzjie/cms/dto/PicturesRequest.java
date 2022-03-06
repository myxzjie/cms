package com.xzjie.cms.dto;

import com.xzjie.cms.model.Pictures;
import com.xzjie.cms.model.PicturesGroup;
import com.xzjie.cms.persistence.annotation.QueryCondition;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class PicturesRequest extends BasePageRequest {

    @QueryCondition
    private Long groupId;

    public Pictures toPictures() {
        Pictures pictures = new Pictures();
        BeanUtils.copyProperties(this, pictures);
        return pictures;
    }
}
