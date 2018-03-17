package com.xzjie.et.ad.service;

import com.xzjie.et.ad.model.Ad;
import com.xzjie.et.ad.model.AdPosition;
import com.xzjie.mybatis.core.service.BaseService;
import com.xzjie.mybatis.page.PageEntity;

import java.util.List;

/**
 * Created by xzjie on 2017/7/2.
 */
public interface AdService extends BaseService<Ad,Long>{

    /**
     * 分页获得广告位置
     * @param pageEntity
     * @return
     */
    PageEntity<AdPosition> getPositionListPage(PageEntity<AdPosition> pageEntity);

    /**
     * 通过广告位置ID获得广告数据
     * @param positionId
     * @return
     */
    List<Ad> getAdByPositionId(Long positionId);
}