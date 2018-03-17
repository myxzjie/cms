package com.xzjie.et.cms.dao;


import com.xzjie.et.cms.model.Site;
import com.xzjie.mybatis.core.dao.BaseMapper;

public interface SiteMapper extends BaseMapper<Site, Long> {

    Site selectSiteByAppId(Long appId);
}