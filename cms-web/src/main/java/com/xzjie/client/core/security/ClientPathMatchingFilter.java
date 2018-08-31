package com.xzjie.client.core.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.xzjie.cache.redis.service.RedisService;
import com.xzjie.et.cms.model.Site;
import com.xzjie.et.cms.service.SiteService;
import com.xzjie.et.core.utils.ConstantsUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Enumeration;

/**
 * @className SystemPathMatchingFilter.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年6月3日 下午11:41:44 
 * @version V0.0.1 
 */
public class ClientPathMatchingFilter extends PathMatchingFilter{

//	@Autowired
//	private RedisService redisService;

	@Autowired
	private SiteService siteService;
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String cid = req.getHeader(ConstantsUtils.ET_CID);
		
		if(cid == null){
			cid = req.getParameter(ConstantsUtils.CID);
		}

		if (cid==null){
			cid = ConstantsUtils.SITE_ID_DEFAULT;
		}

		//Site site = (Site) redisService.get(ConstantsUtils.SITE_ID_KEY+cid);
		Object site = null ;//redisService.get(ConstantsUtils.SITE_ID_KEY+cid);
		if(site == null){
			site = siteService.get(Long.parseLong(cid));
//			redisService.set(ConstantsUtils.SITE_ID_KEY+cid,site,ConstantsUtils.EXPIRE_TIME);
		}

		req.setAttribute("cid",cid);
		return super.preHandle(req, res);
	}

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {

		return super.onPreHandle(request, response, mappedValue);
	}

}
