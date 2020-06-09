package com.xzjie.cms.dto;

import com.google.common.collect.Lists;
import com.xzjie.cms.model.WxTags;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class WxFansTagRequest extends BasePageRequest {
    private Long fansId;
    private List<Long> tagIds = Lists.newArrayList();

//    public WxTags toTags() {
//        WxTags tags = new WxTags();
//        BeanUtils.copyProperties(this, tags);
//        return tags;
//    }
}
