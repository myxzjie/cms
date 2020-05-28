package com.xzjie.cms.dto;

import com.xzjie.cms.model.WxArticleTemplate;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class WxArticleTemplateRequest extends BasePageRequest {
    private String templateName;

    public WxArticleTemplate toArticleTemplate() {
        WxArticleTemplate articleTemplate = new WxArticleTemplate();
        BeanUtils.copyProperties(this, articleTemplate);
        return articleTemplate;
    }
}
