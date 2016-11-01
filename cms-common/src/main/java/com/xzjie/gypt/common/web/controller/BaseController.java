/**
 * radp-cms
 * @Title: BaseController.java 
 * @Package org.radp.xzjie.core.base.web
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年4月22日
 */
package com.xzjie.gypt.common.web.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.xzjie.gypt.common.utils.StringUtils;

/**
 * @className BaseController
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年4月22日 下午2:22:43
 * @version V0.0.1 
 */
public abstract class BaseController{
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected HttpServletRequest request;
	
	@Value("${web.adminPath}")
	protected String adminPath;
	
	@Value("${web.frontPath}")
	protected String frontPath;
	
	@Value("${web.apiPath}")
	protected String apiPath;
	
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		logger.info("String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击>>>:" + System.currentTimeMillis());
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				try {
					setValue(DateUtils.parseDate(text));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			// @Override
			// public String getAsText() {
			// Object value = getValue();
			// return value != null ? DateUtils.formatDateTime((Date)value) :
			// "";
			// }
		});
	}
	public String unescapeHtml4(String value ){
		if(StringUtils.isBlank(value)){
			return null;
		}
		
		//定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
		String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		
		String html= StringEscapeUtils.unescapeHtml4(value);
		Pattern p_script=Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script =p_script.matcher(html);

		return m_script.replaceAll(""); // 过滤script标签

	}
	
	
}
