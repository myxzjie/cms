package com.xzjie.cms.ad.service.impl;

import com.xzjie.cms.ad.convert.AdConverter;
import com.xzjie.cms.ad.dto.AdDto;
import com.xzjie.cms.ad.dto.AdPositionDto;
import com.xzjie.cms.ad.model.Ad;
import com.xzjie.cms.ad.repository.AdPositionRepository;
import com.xzjie.cms.ad.repository.AdRepository;
import com.xzjie.cms.ad.service.AdService;
import com.xzjie.cms.ad.vo.AdVo;
import com.xzjie.cms.core.PageResult;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.ad.model.AdPosition;
import com.xzjie.cms.core.persistence.SpecSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xzjie on 2017/7/2.
 */
@Service
public class AdServiceImpl extends AbstractService<Ad, AdRepository> implements AdService {


    @Autowired
    private AdPositionRepository adPositionRepository;


    public Page<AdPosition> findBookCriteria(Integer page, Integer size, final AdPosition adPosition) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

//        AdPositionSpecification specification = new AdPositionSpecification(new SpecSearchCriteria("positionName", ":", adPosition.getPositionName()));
        Page<AdPosition> bookPage = adPositionRepository.findAll(pageable);

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
    public PageResult<AdVo> getAdPage(AdDto request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("id").descending());
        Specification<Ad> specification = SpecSearchCriteria.builder(request);
        Page<Ad> page = baseRepository.findAll(specification, pageable);
        PageResult<AdVo> pageResult = PageResult.toPage(page.map(AdConverter.INSTANCE::source));
        pageResult.getContent().forEach(ad -> {
            AdPosition position = adPositionRepository.findById(ad.getPositionId()).orElseGet(AdPosition::new);
            ad.setPositionName(position.getPositionName());
            ad.setPositionCode(position.getPositionCode());
        });
        return pageResult;
    }

    @Override
    public Page<AdPosition> getPosition(AdPositionDto query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by("id").descending());
        Specification<AdPosition> specification = SpecSearchCriteria.builder(query);
        return adPositionRepository.findAll(specification, pageable);
    }

    @Override
    public List<Ad> getAdByPositionCode(String positionCode) {
        AdPosition adPosition = adPositionRepository.findAdPositionByPositionCode(positionCode);
        if (adPosition == null) return null;
        return baseRepository.findAdByPositionId(adPosition.getId());
    }

    @Override
    public boolean savePosition(AdPosition position) {
        adPositionRepository.save(position);
        return true;
    }

    @Override
    public boolean updatePosition(AdPosition position) {
        AdPosition model = adPositionRepository.findById(position.getId()).orElseGet(AdPosition::new);

        model.copy(position);
        adPositionRepository.save(model);
        return true;
    }

    @Override
    public boolean deletePosition(Long id) {
        adPositionRepository.deleteById(id);
        return false;
    }

    @Override
    public List<AdPosition> getPositionData() {
        return adPositionRepository.findAll();
    }

//    @Override
//    public boolean update(Ad obj) {
//        Ad ad = baseRepository.findById(obj.getId()).orElseGet(Ad::new);
//        ad.copy(obj);
//        save(ad);
//        return true;
//    }
}
