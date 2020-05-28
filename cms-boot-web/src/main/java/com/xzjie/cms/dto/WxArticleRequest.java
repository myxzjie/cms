package com.xzjie.cms.dto;

import com.google.common.collect.Lists;
import com.xzjie.cms.model.WxArticle;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class WxArticleRequest {

    @NotNull
    private Long id;

    private List<String> deleteArticleIds = Lists.newArrayList();

    private List<WxArticle> list = Lists.newArrayList();
}
