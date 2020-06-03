package com.xzjie.cms.service.impl;

import com.xzjie.cms.convert.WxAccountFansConverter;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.WxOpenIdResult;
import com.xzjie.cms.dto.WxUserResult;
import com.xzjie.cms.model.WxAccountFans;
import com.xzjie.cms.repository.WxAccountFansRepository;
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

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WxAccountFansServiceImpl extends AbstractService<WxAccountFans, Long> implements WxAccountFansService {

    @Autowired
    private WxAccountFansRepository accountFansRepository;

    @Autowired
    private WechatService wechatService;
    @Autowired
    private WxAccountFansConverter accountFansConverter;

    @Override
    protected JpaRepository getRepository() {
        return accountFansRepository;
    }

    @Override
    public boolean update(WxAccountFans obj) {
        return false;
    }

    @Override
    public WxAccountFans getAccountFans(Long id) {
        return accountFansRepository.getOne(id);
    }

    @Override
    public List<WxAccountFans> getAccountFans(WxAccountFans query) {
        return accountFansRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
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
        return accountFansRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query.getNickName() != null) {
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
            if (accountFansRepository.existsByOpenId(openId)) {
                WxAccountFans model = accountFansRepository.findByOpenId(openId);
                model.copy(accountFans);
                accountFansRepository.save(model);
            } else {
                accountFansRepository.save(accountFans);
            }

        }

        if (StringUtils.isNotBlank(openIdResult.getNextOpenid())) {
            this.syncAccountFans(openIdResult.getNextOpenid());
        }
    }
}
