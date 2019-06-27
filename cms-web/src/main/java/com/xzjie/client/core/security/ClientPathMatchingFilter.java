package com.xzjie.client.core.security;

import com.xzjie.cache.ehcache.service.SystemCacheManager;
import com.xzjie.et.cms.model.Site;
import com.xzjie.et.cms.service.SiteService;
import com.xzjie.et.core.utils.ConstantsUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xzjie
 * @version V0.0.1
 * @className ClientPathMatchingFilter.java
 * @description TODO(添加描述)
 * @create 2016年6月3日 下午11:41:44
 */
public class ClientPathMatchingFilter extends PathMatchingFilter {

    @Autowired
    private SiteService siteService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String cid = req.getHeader(ConstantsUtils.ET_CID);

        if (cid == null) {
            cid = req.getParameter(ConstantsUtils.CID);
        }

        if (cid == null) {
            cid = ConstantsUtils.SITE_ID_DEFAULT;
        }

        String key = ConstantsUtils.SITE_ID_KEY + cid;
        Site site = SystemCacheManager.get(key, Site.class);

        if (site == null) {
            site = siteService.get(Long.parseLong(cid));
            SystemCacheManager.put(key, site);
        }

        req.setAttribute("cid", cid);
        return super.preHandle(req, res);
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        return super.onPreHandle(request, response, mappedValue);
    }

}
