package com.xzjie.et.ad.service;

import com.xzjie.mybatis.core.service.BaseService;
import com.xzjie.et.ad.model.AdPosition;

/**
 * Created by xzjie on 2017/7/2.
 */
public interface AdPositionService extends BaseService<AdPosition, Long>  {

    /**
     * 添加信息，返回ID
     * @param t
     * @return
     */
    Long save2(AdPosition t);
}
