package com.xzjie.cms.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.model.SystemLog;
import com.xzjie.cms.repository.SystemLogRepository;
import com.xzjie.cms.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SystemLogServiceImpl extends AbstractService<SystemLog, SystemLogRepository> implements SystemLogService {

    private static final String LOGIN_NAME = "login";


    @Override
    public List<SystemLog> getLoginSystemLog(String username) {
        Pageable pageable = PageRequest.of(0, 100, Sort.by("id").descending());

        Page<SystemLog> list = baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("name").as(String.class), LOGIN_NAME));
            predicates.add(criteriaBuilder.equal(root.get("username").as(String.class), username));
//            LocalDateTime startTime =  LocalDateTime.now().minusDays(30);
//            DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createDate").as(LocalDateTime.class), LocalDateTime.now().minusDays(30)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        return list.getContent();
    }
}
