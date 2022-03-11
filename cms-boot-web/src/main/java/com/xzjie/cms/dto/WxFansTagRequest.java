package com.xzjie.cms.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class WxFansTagRequest extends BasePageDto {
    private Long fansId;
    private List<Long> tagIds = Lists.newArrayList();

//    public WxTags toTags() {
//        WxTags tags = new WxTags();
//        BeanUtils.copyProperties(this, tags);
//        return tags;
//    }
}
