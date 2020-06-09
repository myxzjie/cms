package com.xzjie.cms.dto;

import com.xzjie.cms.model.WxTags;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class WxTagsRequest extends BasePageRequest {
    private Long id;
    private String name;

    public WxTags toTags() {
        WxTags tags = new WxTags();
        BeanUtils.copyProperties(this, tags);
        return tags;
    }
}
