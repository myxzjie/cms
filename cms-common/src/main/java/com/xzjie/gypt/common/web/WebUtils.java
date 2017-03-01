/**
 * @Title: WebUtils.java 
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年4月22日
 */
package com.xzjie.gypt.common.web;

import javax.servlet.http.HttpServletRequest;

/**
 * @className WebUtils
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年4月22日 下午11:25:39
 * @version V0.0.1
 */
public abstract class WebUtils {

	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		int post=request.getServerPort();
		
		String basePath	= request.getScheme()+"://"+request.getServerName();
        if(post!=80){
        	basePath+=":"+request.getServerPort();
        }
        basePath+=path;
        return basePath;
	}

	/**
	 * 获得浏览器语言
	 * 
	 * @param request
	 * @return
	 */
	public static String getLanguage(HttpServletRequest request) {
		return request.getLocale().getLanguage();
	}

//	public static Object getSite(HttpServletRequest request) {
//		String cid = request.getParameter("cid");
//		Object obj = request.getSession().getAttribute(cid);
//		if (obj != null) {
//			return obj;
//		}
//		return null;
//	}
}
