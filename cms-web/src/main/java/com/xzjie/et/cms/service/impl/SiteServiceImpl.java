/**
 * radp-cms
 *
 * @Title: AccountServiceImpl.java
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述)
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月18日
 */
package com.xzjie.et.cms.service.impl;

import java.util.Date;
import java.util.List;

import com.xzjie.et.cms.dao.SiteMapper;
import com.xzjie.et.cms.model.Site;
import com.xzjie.et.cms.service.SiteService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("siteService")
public class SiteServiceImpl extends AbstractBaseService<Site, Long> implements SiteService {

    @Autowired
    private SiteMapper siteMapper;

    @Override
    protected BaseMapper<Site, Long> getMapper() {
        return siteMapper;
    }

    @Override
    public Site getSiteByAppId(Long appId) {
        return siteMapper.selectSiteByAppId(appId);
    }

}
