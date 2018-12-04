package com.xzjie.client.core.web;

import com.xzjie.cache.ehcache.service.SystemCacheManager;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.cms.model.Site;
import com.xzjie.et.cms.service.SiteService;
import com.xzjie.et.core.utils.ConstantsUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    @Autowired
    protected HttpServletRequest request;


    @Value(value = "${template.name}")
    private String template;


    /**
     * 获得前端路由前缀
     * @param view
     * @return
     */
    protected String getRemoteView(String view) {

        Site site = SystemCacheManager.get(ConstantsUtils.SITE_ID_KEY + getSiteId(), Site.class);
        String remote = "";
        if (site != null && StringUtils.isNotBlank(site.getTheme())) {
            remote = site.getTheme() + "/" + view;
        } else {
            remote = template + "/" + view;
        }
        return remote;
    }

    public Long getSiteId() {
        String cid = request.getHeader(ConstantsUtils.ET_CID);

        if (cid == null) {
            cid = request.getParameter(ConstantsUtils.CID);
        }

        if (cid == null) {
            cid = ConstantsUtils.SITE_ID_DEFAULT;
        }
        return Long.parseLong(cid);

    }

    public Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    /**
     * 获取授权主要对象
     */
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
}
