package com.xzjie.cms.dto;

import com.xzjie.cms.picture.model.Pictures;
import com.xzjie.cms.core.persistence.annotation.QueryCondition;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class PictureQueryDto extends BasePageDto {

    @QueryCondition
    private Long groupId;

    public Pictures toPictures() {
        Pictures pictures = new Pictures();
        BeanUtils.copyProperties(this, pictures);
        return pictures;
    }
}
