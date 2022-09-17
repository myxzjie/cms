package com.xzjie.cms.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.LabelQueryDto;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.model.Label;
import com.xzjie.cms.persistence.SpecSearchCriteria;
import com.xzjie.cms.repository.LabelRepository;
import com.xzjie.cms.service.LabelService;
import com.xzjie.cms.vo.LabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl extends AbstractService<Label, LabelRepository> implements LabelService {

    @Override
    public boolean update(Label obj) {
        Label model = baseRepository.findById(obj.getId()).orElseGet(Label::new);
        model.copy(obj);
        model.setUpdateDate(LocalDateTime.now());
         save(model);
         return true;
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
