package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.Label;
import com.xzjie.cms.vo.LabelVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LabelService extends BaseService<Label> {

    List<Label> getLabelList();

    List<LabelVo> getLabelCounterList();

    List<Label> getLabelByArticleId(Long articleId);

    Page<Label> getLabel(Integer page, int size, Label query);
}
