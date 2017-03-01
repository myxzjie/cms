package com.xzjie.init;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.xzjie.cache.SystemCacheManager;
import com.xzjie.gypt.cms.model.Site;
import com.xzjie.gypt.cms.service.SiteService;

@Component
public class SystemInitManager implements  ServletContextAware{
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private final String SITE_KEY="site";
	
	@Value(value = "${web.cid}")
	private Long siteId;
	
	@Autowired
	private SiteService siteService;
	@Autowired
	private SystemCacheManager systemCache;

	@Override
	public void setServletContext(ServletContext servletContext) {
		// 把项目名称放到application中  
//		String ctxPath=servletContext.getContextPath(); 
//		servletContext.setAttribute("ctxPath",ctxPath);
        initSite(servletContext);
	}
	
	public void initSite(ServletContext servletContext){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n=====   Site info load start  =====\r\n");
		
		logger.info(sb.toString());
		Site site=null;
		try {
			site = (Site) systemCache.get(SITE_KEY);
		} catch (Exception e) {
			site = siteService.get(siteId);
			systemCache.put(SITE_KEY, site);
		}
		
		servletContext.setAttribute("site", site);
		
		sb.setLength(0); 
		sb.append("\r\n=====   Site info load end  =====\r\n");
		logger.info(sb.toString());
	}
}
