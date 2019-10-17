package com.xzjie.cms.dto;

import com.xzjie.cms.model.PicturesGroup;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class PicturesGroupRequest {
    @NotBlank
    private String name;

    public PicturesGroup toPicturesGroup() {
        PicturesGroup group = new PicturesGroup();
        BeanUtils.copyProperties(this, group);
        return group;
    }
}
