package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.Ad;

import java.util.List;

/**
 * Created by xzjie on 2017/7/2.
 */
public interface AdService extends BaseService<Ad,Long> {

//    /**
//     * 分页获得广告位置
//     * @param pageEntity
//     * @return
//     */
//    PageEntity<AdPosition> getPositionListPage(PageEntity<AdPosition> pageEntity);

    /**
     * 通过广告位置ID获得广告数据
     * @param positionCode
     * @return
     */
    List<Ad> getAdByPositionCode(String positionCode);
}
