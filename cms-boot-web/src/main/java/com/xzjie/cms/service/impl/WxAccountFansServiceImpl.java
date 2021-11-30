package com.xzjie.cms.service.impl;

import com.xzjie.cms.convert.WxAccountFansConverter;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.WxOpenIdResult;
import com.xzjie.cms.dto.WxUserResult;
import com.xzjie.cms.model.WxAccountFans;
import com.xzjie.cms.model.WxFansTag;
import com.xzjie.cms.model.WxTags;
import com.xzjie.cms.repository.WxFansRepository;
import com.xzjie.cms.service.WechatService;
import com.xzjie.cms.service.WxAccountFansService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WxAccountFansServiceImpl extends AbstractService<WxAccountFans, WxFansRepository> implements WxAccountFansService {


    @Autowired
    private WechatService wechatService;
    @Autowired
    private WxAccountFansConverter accountFansConverter;


    @Override
    public boolean update(WxAccountFans obj) {
        return false;
    }

    @Override
    public WxAccountFans getAccountFans(Long id) {
        return baseRepository.getOne(id);
    }

    @Override
    public List<WxAccountFans> getAccountFans(WxAccountFans query) {
        return baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.getNickName() != null) {
                predicates.add(criteriaBuilder.like(root.get("nickName"), query.getNickName() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }

    @Override
    public Page<WxAccountFans> getAccountFans(Integer page, int size, WxAccountFans query) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.getNickName() != null) {
                predicates.add(criteriaBuilder.like(root.get("nickName"), query.getNickName() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public Page<WxAccountFans> getAccountFans(Integer page, int size, WxAccountFans query, Long tagId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (tagId != null && tagId > 0) {
                Join<WxAccountFans, WxFansTag> tagsJoin = root.join("fansTags", JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(tagsJoin.get("tagId"), tagId));
                criteriaQuery.distinct(true);
            }
            if (StringUtils.isNotBlank(query.getNickName())) {
                predicates.add(criteriaBuilder.like(root.get("nickName"), query.getNickName() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public void syncAccountFans(String nextOpenId) {
        WxOpenIdResult openIdResult = wechatService.getOpenIds(nextOpenId);
        for (String openId : openIdResult.getOpenids()) {
            WxUserResult userResult = wechatService.getUser(openId, "");
            WxAccountFans accountFans = accountFansConverter.target(userResult);
            accountFans.setState(1);
            if (baseRepository.existsByOpenId(openId)) {
                WxAccountFans model = baseRepository.findByOpenId(openId);
                model.copy(accountFans);

                baseRepository.save(model);
            } else {
                baseRepository.save(accountFans);
            }

        }

        if (StringUtils.isNotBlank(openIdResult.getNextOpenid())) {
            this.syncAccountFans(openIdResult.getNextOpenid());
        }
    }
}
