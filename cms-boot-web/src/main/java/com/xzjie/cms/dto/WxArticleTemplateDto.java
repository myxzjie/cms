package com.xzjie.cms.dto;

import com.google.common.collect.Lists;
import com.xzjie.cms.model.WxArticleTemplate;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class WxArticleTemplateDto extends BasePageDto {
    private Long id;
    private Long tagId;
    private String templateName;
    private Boolean publish;

    private List<Long> previewFansIds = Lists.newArrayList();
    private List<Long> fansIds = Lists.newArrayList();

    public WxArticleTemplate toArticleTemplate() {
        WxArticleTemplate articleTemplate = new WxArticleTemplate();
        BeanUtils.copyProperties(this, articleTemplate);
        return articleTemplate;
    }
}
