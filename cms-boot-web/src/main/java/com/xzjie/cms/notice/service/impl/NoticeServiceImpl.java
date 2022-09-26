package com.xzjie.cms.notice.service.impl;

import com.xzjie.cms.core.persistence.SpecSearchCriteria;
import com.xzjie.cms.core.persistence.SpecificationWrapper;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.label.model.Label;
import com.xzjie.cms.notice.dto.NoticeQueryDto;
import com.xzjie.cms.notice.model.Notice;
import com.xzjie.cms.notice.repository.NoticeRepository;
import com.xzjie.cms.notice.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author vito
 * @date : 2022/9/23 12:31 PM
 */
@Service
public class NoticeServiceImpl extends AbstractService<Notice, NoticeRepository> implements NoticeService {


    @Override
    public List<Notice> getNotices() {
        LocalDateTime nowDate = LocalDateTime.now();
//        return baseRepository.findAll(PredicateWrapper.and()
//                .lessThanOrEqualTo(SystemNoticeController::getStartDate, nowDate)
//                .greaterThanOrEqualTo(SystemNoticeController::getEndDate, nowDate)
//                .build());

        return baseRepository.findAll(SpecificationWrapper.toSpecAnd()
//                .lessThanOrEqualTo("startDate", nowDate)
//                .greaterThanOrEqualTo("endDate", nowDate)
                .lessThanOrEqualTo(Notice::getStartDate, nowDate)
                .greaterThanOrEqualTo(Notice::getEndDate, nowDate)
                .build());


//        return baseRepository.findAll((root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            Expression<LocalDateTime> startDate = root.get("startDate").as(LocalDateTime.class);
//            Expression<LocalDateTime> endDate = root.get("endDate").as(LocalDateTime.class);
//            LocalDateTime nowDate = LocalDateTime.now();
//            // 开始时间
//            predicates.add(cb.lessThanOrEqualTo(startDate, nowDate));
//            // 结束时间
//            predicates.add(cb.greaterThanOrEqualTo(endDate, nowDate));
//
//            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//        });
    }

    @Override
    public Page<Notice> getNotices(NoticeQueryDto query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by("id").descending());
        return baseRepository.findAll(SpecSearchCriteria.builder(query), pageable);
    }
}
