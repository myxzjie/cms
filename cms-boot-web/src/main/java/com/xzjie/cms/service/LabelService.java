package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.Label;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LabelService extends BaseService<Label,Long> {

    List<Label> getLabelByArticleId(Long articleId);

    Page<Label> getLabel(Integer page, int size, Label query);
}
