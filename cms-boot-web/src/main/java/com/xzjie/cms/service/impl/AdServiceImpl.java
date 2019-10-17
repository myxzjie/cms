package com.xzjie.cms.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.model.AdPosition;
import com.xzjie.cms.repository.AdPositionRepository;
import com.xzjie.cms.repository.AdRepository;
import com.xzjie.cms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xzjie on 2017/7/2.
 */
@Service
public class AdServiceImpl extends AbstractService<Ad, Long> implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdPositionRepository adPositionRepository;

    @Override
    protected JpaRepository getRepository() {
        return adRepository;
    }

    public Page<AdPosition> findBookCriteria(Integer page, Integer size, final AdPosition adPosition) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

//        AdPositionSpecification specification = new AdPositionSpecification(new SpecSearchCriteria("positionName", ":", adPosition.getPositionName()));
        Page<AdPosition> bookPage = adPositionRepository.findAll(pageable);
        /*adPositionRepository.findAll(new Specification<AdPosition>() {
            @Override
            public Predicate toPredicate(Root<AdPosition> root, CriteriaQuery<AdPosition> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> list = new ArrayList<Predicate>();
//                if (null != bookQuery.getName() && !"".equals(bookQuery.getName())) {
//                    list.add(criteriaBuilder.equal(root.get("name").as(String.class), bookQuery.getName()));
//                }
//                if (null != bookQuery.getIsbn() && !"".equals(bookQuery.getIsbn())) {
//                    list.add(criteriaBuilder.equal(root.get("isbn").as(String.class), bookQuery.getIsbn()));
//                }
//                if (null != bookQuery.getAuthor() && !"".equals(bookQuery.getAuthor())) {
//                    list.add(criteriaBuilder.equal(root.get("author").as(String.class), bookQuery.getAuthor()));
//                }
//                Predicate[] p = new Predicate[list.size()];
//                return criteriaBuilder.and(list.toArray(p));
                return null;
            }
        }, pageable);*/
        return bookPage;
    }



  /*  @Override
    public PageEntity<AdPosition> getPositionListPage(PageEntity<AdPosition> pageEntity) {
        List<AdPosition> list = adPositionMapper.selectListPage(pageEntity);
        pageEntity.setRows(list);
        return pageEntity;
    }*/

//    @Override
//    public Page<Book> findBookNoCriteria(Integer page,Integer size) {
//        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
//        return bookRepository.findAll(pageable);
//    }


    @Override
    public List<Ad> getAdByPositionCode(String positionCode) {
        AdPosition adPosition = adPositionRepository.findAdPositionByPositionCode(positionCode);
        if (adPosition == null) return null;
        return adRepository.findAdByPositionId(adPosition.getId());
    }

    @Override
    public boolean update(Ad obj) {
        Ad ad = adRepository.findById(obj.getId()).orElseGet(Ad::new);
        return save(ad);
    }
}
