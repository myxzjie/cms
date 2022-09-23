package com.xzjie.cms.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xzjie.cms.convert.WxTagsConverter;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.WxTagging;
import com.xzjie.cms.dto.WxTagsCreate;
import com.xzjie.cms.dto.WxTagsResult;
import com.xzjie.cms.model.WxAccountFans;
import com.xzjie.cms.model.WxFansTag;
import com.xzjie.cms.model.WxTags;
import com.xzjie.cms.repository.WxFansTagRepository;
import com.xzjie.cms.repository.WxTagsRepository;
import com.xzjie.cms.service.WechatService;
import com.xzjie.cms.service.WxAccountFansService;
import com.xzjie.cms.service.WxTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class WxTagsServiceImpl extends AbstractService<WxTags, WxTagsRepository> implements WxTagsService {
    @Autowired
    private WxFansTagRepository fansTagRepository;

    @Autowired
    private WxAccountFansService accountFansService;
    @Autowired
    private WechatService wechatService;
    @Autowired
    private WxTagsConverter tagsConverter;


    @Override
    public WxTags save(WxTags obj) {
        WxTagsResult result = wechatService.createTags(WxTagsCreate.builder().setName(obj.getName()).build());
        obj.setId(result.getId());
        return super.save(obj);
    }

    @Override
    public WxTags update(WxTags entity) {
//        WxTags model = baseRepository.getOne(obj.getId());
        wechatService.updateTags(WxTagsCreate.builder().setId(entity.getId()).setName(entity.getName()).build());
//        model.copy(obj);
//        baseRepository.save(model);
        return save(entity);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        wechatService.deleteTags(WxTagsCreate.builder().setId(id).build());
        baseRepository.deleteById(id);
        fansTagRepository.deleteByTagId(id);
        return true;
    }

    @Override
    public Page<WxTags> getTags(Integer page, Integer size, WxTags query) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query == null) {
                return null;
            }
            if (null != query.getName()) {
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), query.getName() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }


    @Override
    public List<WxTags> getTagsData() {
        return baseRepository.findAll();
    }

    @Override
    public void syncTags() {
        List<WxTagsResult> tagsResults = wechatService.getTags();
        for (WxTagsResult tagsResult : tagsResults) {
            WxTags tags = tagsConverter.target(tagsResult);

            if (baseRepository.existsById(tags.getId())) {
                WxTags model = baseRepository.getOne(tags.getId());
                model.copy(tags);
                baseRepository.save(model);
            } else {
                baseRepository.save(tags);
            }
        }
    }

    @Override
    public Set<Long> getByFansId(Long fansId) {
        Set<Long> tags = Sets.newHashSet();
        List<WxFansTag> fansTags = fansTagRepository.findByFansId(fansId);
        fansTags.forEach(fansTag -> {
            tags.add(fansTag.getTagId());
        });
        return tags;
    }


    @Override
    @Transactional
    public void saveFans(Long fansId, List<Long> tagIds) {
        fansTagRepository.deleteByFansId(fansId);
        if (tagIds.size() > 0) {
            WxAccountFans accountFans = accountFansService.getAccountFans(fansId);
            tagIds.forEach(tagId -> {
                WxFansTag fansTag = new WxFansTag();
                fansTag.setFansId(fansId);
                fansTag.setTagId(tagId);
                fansTag.setOpenId(accountFans.getOpenId());

                wechatService.batchTagging(WxTagging.builder().setTagId(tagId).setOpenId(accountFans.getOpenId()).build());
                fansTagRepository.save(fansTag);
            });
        }
    }
}
