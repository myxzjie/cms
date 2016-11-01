/**
 * radp-cms
 * @Title: WebContextListener.java 
 * @Package org.radp.xzjie.core.listener
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年4月22日
 */
package com.xzjie.gypt.common.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @className WebContextListener
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年4月22日 下午2:24:59
 * @version V0.0.1 
 */
public class WebContextListener extends ContextLoaderListener{

	private static final Logger logger = LoggerFactory.getLogger(WebContextListener.class);
	/**
	 * 停止
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    停止使用 CMS  - Powered By \r\n");
		sb.append("\r\n======================================================================\r\n");
		
		logger.info(sb.toString());
		super.contextDestroyed(event);
	}

	/**
	 * 启动
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 CMS  - Powered By \r\n");
		sb.append("\r\n======================================================================\r\n");
		
		logger.info(sb.toString());
		super.contextInitialized(event);
	}

}
