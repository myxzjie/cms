package com.xzjie.cms.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.model.Label;
import com.xzjie.cms.repository.LabelRepository;
import com.xzjie.cms.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelServiceImpl extends AbstractService<Label, LabelRepository> implements LabelService {


    @Override
    public boolean update(Label obj) {
        return false;
    }

    @Override
    public List<Label> getLabelByArticleId(Long articleId) {
        return baseRepository.findByArticleId(articleId);
    }

    @Override
    public Page<Label> getLabel(Integer page, int size, Label query) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query == null) {
                return null;
            }
//            if (null != query.getPid()) {
//                predicates.add(criteriaBuilder.equal(root.get("pid").as(String.class), query.getPid()));
//            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }
}
