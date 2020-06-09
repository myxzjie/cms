package com.xzjie.cms.dto;

import com.google.common.collect.Lists;
import com.xzjie.cms.model.WxArticleTemplate;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class WxArticleTemplateRequest extends BasePageRequest {
    private Long id;
    private String templateName;
    private Boolean publish;

    private List<Long> fansIds = Lists.newArrayList();

    public WxArticleTemplate toArticleTemplate() {
        WxArticleTemplate articleTemplate = new WxArticleTemplate();
        BeanUtils.copyProperties(this, articleTemplate);
        return articleTemplate;
    }
}
