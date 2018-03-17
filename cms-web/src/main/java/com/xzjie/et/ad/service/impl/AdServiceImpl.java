package com.xzjie.et.ad.service.impl;

import com.xzjie.et.ad.dao.AdPositionMapper;
import com.xzjie.et.ad.model.AdPosition;
import com.xzjie.et.ad.service.AdService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import com.xzjie.et.ad.dao.AdMapper;
import com.xzjie.et.ad.model.Ad;
import com.xzjie.mybatis.page.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xzjie on 2017/7/2.
 */
@Service("adService")
public class AdServiceImpl extends AbstractBaseService<Ad,Long> implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Autowired
    private AdPositionMapper adPositionMapper;

    @Override
    protected BaseMapper<Ad, Long> getMapper() {
        return adMapper;
    }

    @Override
    public PageEntity<AdPosition> getPositionListPage(PageEntity<AdPosition> pageEntity) {
        List<AdPosition> list= adPositionMapper.selectListPage(pageEntity);
        pageEntity.setRows(list);
        return pageEntity;
    }

    @Override
    public List<Ad> getAdByPositionId(Long positionId) {
        Ad model=new Ad();
        model.setEnabled(true);
        model.setPositionId(positionId);

        return adMapper.selectList(model);
    }
}
