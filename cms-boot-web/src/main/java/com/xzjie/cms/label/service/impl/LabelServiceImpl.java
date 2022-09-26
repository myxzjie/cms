package com.xzjie.cms.label.service.impl;

import com.xzjie.cms.core.persistence.SpecSearchCriteria;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.LabelQueryDto;
import com.xzjie.cms.label.repository.LabelRepository;
import com.xzjie.cms.label.service.LabelService;
import com.xzjie.cms.label.model.Label;
import com.xzjie.cms.vo.LabelVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LabelServiceImpl extends AbstractService<Label, LabelRepository> implements LabelService {

    @Override
    public Label update(Label entity) {
//        Label model = baseRepository.findById(obj.getId()).orElseGet(Label::new);
//        model.copy(obj);
        entity.setUpdateDate(LocalDateTime.now());
//         save(model);
         return super.update(entity);
    }

    @Override
    public List<Label> getLabelList() {
        return baseRepository.findAll();
    }

    @Override
    public List<LabelVo> getLabelCounterList() {
        return baseRepository.findLabelAll();
    }

    @Override
    public List<Label> getLabelByArticleId(Long articleId) {
        return baseRepository.findByArticleId(articleId);
    }

    @Override
    public Page<Label> getLabel(LabelQueryDto query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by("id").descending());
        Specification<Label> specification = SpecSearchCriteria.builder(query);
        return baseRepository.findAll(specification,pageable);
    }
}
