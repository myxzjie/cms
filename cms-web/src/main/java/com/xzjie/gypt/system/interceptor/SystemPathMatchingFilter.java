/**
 * radp-cms
 * @Title: SystemPathMatchingFilter.java 
 * @Package org.radp.xzjie.module.system.interceptor
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月3日
 */
package com.xzjie.gypt.system.interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * @className SystemPathMatchingFilter.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年6月3日 下午11:41:44 
 * @version V0.0.1 
 */
public class SystemPathMatchingFilter extends PathMatchingFilter{

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(request, response);
	}

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		return super.onPreHandle(request, response, mappedValue);
	}

}
