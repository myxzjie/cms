package com.xzjie.cms.dto;

import com.xzjie.cms.model.WxTags;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class WxTagsRequest extends BasePageDto {
    private Long id;
    private String name;

    public WxTags toTags() {
        WxTags tags = new WxTags();
        BeanUtils.copyProperties(this, tags);
        return tags;
    }
}
