/**
 * radp-cms
 * @Title: CrosFilter.java 
 * @Package com.xzjie.gypt.system.interceptor
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月2日
 */
package com.xzjie.gypt.system.interceptor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className CrosFilter.java
 * @description 用于跨域共享session,那个应用需要与登录应用共享session，就开放此filter，并且应该是拦截所有请求，至少是拦截所有需要保护的资源 
 * @author xzjie
 * @create 2016年9月2日 上午9:57:42 
 * @version V0.0.1 
 */
public class CrosFilter extends AdviceFilter{

	private final static Logger log = LoggerFactory.getLogger(CrosFilter.class);  
    
    private String casServerURL;  //重定向的目标地址，该地址用于获取sessionId,如www.b.com/token  
    private String domain;   //filter应用的域名，如www.a.com  
  
    @Override  
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse)throws Exception {  
    	HttpServletResponse response = (HttpServletResponse) servletResponse; 
    	String origin = (String) servletRequest.getRemoteHost()+":"+servletRequest.getRemotePort(); 
    	/*response.setHeader("Access-Control-Allow-Origin", "*"); 
    	response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); 
    	response.setHeader("Access-Control-Max-Age", "3600"); 
    	response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization"); 
    	response.setHeader("Access-Control-Allow-Credentials","true"); */
    	
    	return super.preHandle(servletRequest, response);
    }  
  
    @Override  
    protected void postHandle(ServletRequest request, ServletResponse response)throws Exception {  
        super.postHandle(request, response);  
    }  
  
    public void setCasServerURL(String casServerURL) {  
        this.casServerURL = casServerURL;  
    }  
  
    public void setDomain(String domain) {  
        this.domain = domain;  
    }  
}
