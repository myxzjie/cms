package com.xzjie.et.ad.service.impl;

import com.xzjie.et.ad.service.AdPositionService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import com.xzjie.et.ad.dao.AdPositionMapper;
import com.xzjie.et.ad.model.AdPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by xzjie on 2017/7/2.
 */
@Service("adPositionService")
public class AdPositionServiceImpl extends AbstractBaseService<AdPosition,Long> implements AdPositionService{

    @Autowired
    private AdPositionMapper adPositionMapper;

    @Override
    protected BaseMapper<AdPosition, Long> getMapper() {
        return adPositionMapper;
    }

    @Override
    public Long save2(AdPosition t) {
        t.setCreateDate(new Date());
        save(t);
        return t.getId();
    }
}
