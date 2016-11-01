/**
 * ask_rear
 * @Title: HttpUtils.java 
 * @Package com.ask.rear.utils
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年2月1日
 *//*
package com.xzjie.gypt.common.utils;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

*//**
 * @className HttpUtils
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年2月1日 下午8:34:40
 * @version V0.0.1 
 *//*
public class HttpUtils_old {

	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	// 超时设置
	// 连接超时 秒单位
	private static final int CONN_TIMEOUT = 10000;
	// 数据读取超时 秒单位
	private static final int SOCKET_TIMEOUT = 10000;
	// 编码格式
	private static final String CHARSET = Language.CODE_UTF_8.toString();
	
	public static String doPostJson(String url, String json) throws Exception {
		PostMethod method = new PostMethod(url);

		method.setRequestHeader("Content-Type", "application/json;charset=" + CHARSET);
		
		RequestEntity entity = new StringRequestEntity(json, "text/json", CHARSET);
		
		method.setRequestEntity(entity);

		return doHttp(method);
	}
	
	public static String doPostXML(String url, String xml) throws Exception {
		PostMethod method = new PostMethod(url);
		
		method.setRequestHeader("Content-type", "application/xml; charset=" + CHARSET);
		
		// 设置请求体，即xml文本内容，
		//注：这里写了两种方式，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式
		RequestEntity entity = new StringRequestEntity(xml, "text/xml", CHARSET);
		
		method.setRequestEntity(entity);

		return doHttp(method);
	}
	
	
	private static String doHttp(HttpMethod method) throws Exception {
		HttpClient httpClient = null;
		HostConfiguration configuration = null;
		Credentials defaultcreds = null;
		String result = "";

		try {
			httpClient = new HttpClient();

			defaultcreds = null;
			httpClient.getState().setCredentials(AuthScope.ANY, defaultcreds);
			httpClient.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
			httpClient.getParams().setParameter("http.protocol.content-charset", CHARSET);

			configuration = new HostConfiguration();
			configuration.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_0);
			// 连接超时
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT);
			// 数据读取超时
			httpClient.getHttpConnectionManager().getParams().setSoTimeout(SOCKET_TIMEOUT);

			httpClient.executeMethod(method);

			if (method.getStatusCode() == HttpStatus.SC_OK) {
				result = method.getResponseBodyAsString();
				logger.debug("============================ http return :" + result);
			} else {
				result = "未知错误: " + method.getStatusLine().toString();
				throw new Exception(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("============================ http error>>>>"+e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			method.releaseConnection();
		}

		return result;
	}
	
}
*/