/**
 * radp-cms
 * @Title: StringUtils.java 
 * @Package org.radp.xzjie.core.utils
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月10日
 */
package com.xzjie.gypt.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @className StringUtils.java
 * @description 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * @author xzjie
 * @create 2016年6月10日 下午11:48:54 
 * @version V0.0.1 
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

	/**
	 * 获得用户远程地址
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
}
