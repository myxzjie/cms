package com.xzjie.gypt.cms.interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.xzjie.gypt.cms.model.Site;
import com.xzjie.gypt.cms.service.SiteService;
import com.xzjie.gypt.common.utils.RequestHelper;
import com.xzjie.gypt.common.utils.StringUtils;

public class CMSFilter extends PathMatchingFilter {

	private String[] regx = new String[] { ".jpg", ".jpeg", ".gif", ".png", ".ico", ".js", ".css", ".woff2", ".woff",
			".eot" };

	@Autowired
	private SiteService siteService;

	@Override
	protected boolean preHandle(ServletRequest req, ServletResponse response) throws Exception {

		HttpServletRequest request = (HttpServletRequest) req;

		String uri = request.getRequestURI();
		if (RequestHelper.isEndsWith(uri, regx)) {
			return super.preHandle(request, response);
		}

		String siteId = request.getParameter("cid");

		if (StringUtils.isBlank(siteId)) {
			String url = request.getScheme() + "://" + request.getServerName();
			if (request.getServerPort() != 80) {
				url += ":" + request.getServerPort();
			}
			url += request.getRequestURI();
			throw new Exception("cid 参数错误. url:" + url);
		}

		Object obj = request.getSession().getAttribute(siteId);

		if (obj == null) {
			Site site = siteService.get(Long.parseLong(siteId));
			request.getSession().setAttribute(siteId, site);
		}

		return super.preHandle(request, response);
	}

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		return super.onPreHandle(request, response, mappedValue);
	}
}
