package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.dto.AdPositionDto;
import com.xzjie.cms.dto.AdDto;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.model.AdPosition;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by xzjie on 2017/7/2.
 */
public interface AdService extends BaseService<Ad> {

    Page<Ad> getAd(AdDto request);

    /**
     * 分页获得广告位置
     *
     * @param page
     * @param size
     * @param query
     * @return
     */
    Page<AdPosition> getPosition(AdPositionDto query);

    /**
     * 通过广告位置ID获得广告数据
     *
     * @param positionCode
     * @return
     */
    List<Ad> getAdByPositionCode(String positionCode);

    boolean savePosition(AdPosition position);

    boolean updatePosition(AdPosition position);

    boolean deletePosition(Long id);


    List<AdPosition> getPositionData();
}
