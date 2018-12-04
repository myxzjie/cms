package com.xzjie.client.cms.web.tags;

import java.io.IOException;
import java.util.Map;

import com.xzjie.cache.ehcache.service.SystemCacheManager;
import com.xzjie.common.annotation.freemarker.FreemarkerComponent;
import com.xzjie.common.utils.SpringUtils;
import com.xzjie.common.web.freemarker.AbstractTemplateDirectiveModel;
import com.xzjie.et.cms.model.Site;
import com.xzjie.et.cms.service.SiteService;

import com.xzjie.et.core.utils.ConstantsUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

@FreemarkerComponent("site")
public class SiteTags extends AbstractTemplateDirectiveModel {
    @Autowired
    private SiteService siteService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        if (params.get("id") == null) {
            return;
        }
        String alias = params.get("alias") == null ? null : params.get("alias").toString();
        String name = alias == null ? "site" : alias;
        String id = params.get("id").toString();
        Site site = SystemCacheManager.get(ConstantsUtils.SITE_ID_KEY + id, Site.class);
        if (site == null) {
            site = siteService.get(Long.parseLong(id));
        }
        env.setVariable(name, wrap(site));
        if (body != null) {
            body.render(env.getOut());
        }
        // env.getOut().write(site);
    }
}
