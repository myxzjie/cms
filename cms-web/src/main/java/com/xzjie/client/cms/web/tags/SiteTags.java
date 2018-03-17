package com.xzjie.client.cms.web.tags;

import java.io.IOException;
import java.util.Map;

//import com.xzjie.cache.redis.service.RedisService;
import com.xzjie.common.utils.SpringUtils;
import com.xzjie.common.web.freemarker.AbstractTemplateDirectiveModel;
import com.xzjie.et.cms.model.Site;
import com.xzjie.et.cms.service.SiteService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class SiteTags extends AbstractTemplateDirectiveModel {
	private SiteService siteService = SpringUtils.getBean(SiteService.class);
	// private RedisService redisService =SpringUtils.getBean(RedisService.class);

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if (params.get("id") == null)
			return;
		String alias = params.get("alias") == null ? null : params.get("alias").toString();
		String name = alias == null ? "site" : alias;
		String id = params.get("id").toString();
		Site site = null;// (Site) redisService.get(ConstantsUtils.SITE_ID_KEY+id);
		site = siteService.get(Long.parseLong(id));
		env.setVariable(name, wrap(site));
		if (body != null) {
			body.render(env.getOut());
		}
		// env.getOut().write(site);
	}
}
