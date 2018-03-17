
package com.xzjie.et.cms.service;


import com.xzjie.et.cms.model.Site;
import com.xzjie.mybatis.core.service.BaseService;

public interface SiteService extends BaseService<Site, Long> {

    Site getSiteByAppId(Long appId);
}
