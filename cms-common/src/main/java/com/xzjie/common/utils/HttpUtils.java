/**
 * @Title: HttpUtils.java
 * @Description: TODO(添加描述)
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月4日
 */
package com.xzjie.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.xzjie.common.core.http.HttpProtocol;

/**
 * @author xzjie
 * @version V0.0.1
 * @className HttpUtils.java
 * @description TODO(添加描述)
 * @create 2016年9月4日 下午9:41:05
 */
public class HttpUtils {
	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 5000;
	private static final String ENCODING = "UTF-8";

	// constants
	public static final String APPLICATION_ATOM_XML = "application/atom+xml";
	public static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
	public static final String APPLICATION_JSON = "application/json";
	public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
	public static final String APPLICATION_SVG_XML = "application/svg+xml";
	public static final String APPLICATION_XHTML_XML = "application/xhtml+xml";
	public static final String APPLICATION_XML = "application/xml";
	public static final String MULTIPART_FORM_DATA = "multipart/form-data";
	public static final String TEXT_HTML = "text/html";
	public static final String TEXT_PLAIN = "text/plain";
	public static final String TEXT_XML = "text/xml";
	public static final String WILDCARD = "*/*";

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
		// 在提交请求之前 测试连接是否可用(setStaleConnectionCheckEnabled过期方法已被替换)
        connMgr.setValidateAfterInactivity(300);

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		// 在提交请求之前 测试连接是否可用
		// configBuilder.setStaleConnectionCheckEnabled(true); //过期方法
		// configBuilder.setContentCompressionEnabled(true);
		requestConfig = configBuilder.build();
	}
	

	/**
	 * 发送 GET 请求（HTTP），不带输入数据
	 *
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, new HashMap<String, Object>(0));
	}

	/**
	 * 发送 GET 请求（HTTP），K-V形式
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGet(String url, Map<String, Object> params) {
		String apiUrl = url;
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		apiUrl += param;
		String result = null;
		// HttpClient httpclient = new DefaultHttpClient();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpPost = new HttpGet(apiUrl);
			HttpResponse response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();

			System.out.println("执行状态码 : " + statusCode);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = IOUtils.toString(instream, ENCODING);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 发送 POST 请求（HTTP），不带输入数据
	 *
	 * @param apiUrl
	 * @return
	 */
	public static String doPost(String apiUrl) {
		return doPost(apiUrl, new HashMap<String, Object>(0));
	}

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 *
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return
	 */
	public static String doPost(String apiUrl, Map<String, Object> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(ENCODING)));
			response = httpClient.execute(httpPost);
			System.out.println(response.toString());
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * 发送 POST 请求（HTTP），JSON形式
	 *
	 * @param apiUrl
	 * @param params
	 *            json对象或者aaa=1&bbb=2
	 * @return
	 */
	public static String doPost(String apiUrl, String params) {
		CloseableHttpClient httpClient = getHttpClient(apiUrl);
		String result = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(params, "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			System.out.println(response.getStatusLine().getStatusCode());
			result = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String doHttp(HttpRequestBase remoteRequest, HttpProtocol protocol) {
		CloseableHttpClient httpClient = getHttpClient(protocol);
		CloseableHttpResponse response = null;
		String responseContent = null;

		try {
			response = httpClient.execute(remoteRequest);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == 200) {
					responseContent = EntityUtils.toString(entity, ENCODING);
				} else {
					responseContent = "请求状态异常：" + response.getStatusLine().getStatusCode();
					if (statusCode == 302) {
						responseContent += ";Redirect地址：" + response.getHeaders("Location");
					}
				}
				// EntityUtils.consume(entity);
			}
		} catch (IOException e) {
			e.printStackTrace();
			responseContent = "请求异常:" + e.getMessage();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseContent;
	}

	/**
	 *
	 * @param apiUrl
	 * @param file
	 * @param media
	 *            修改文件名称
	 * @return
	 */
	public static String doPostFile(String apiUrl, File file, String media) {
		CloseableHttpClient httpClient = getHttpClient(apiUrl);
		CloseableHttpResponse response = null;
		String responseContent = null;

		HttpPost httpPost = new HttpPost(apiUrl);
		httpPost.setConfig(requestConfig);

		try {
			HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody(media, file)
					.setMode(HttpMultipartMode.RFC6532).build();

			httpPost.setEntity(reqEntity);

			response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			if (null != entity) {
				int statusCode = response.getStatusLine().getStatusCode();
				System.out.println("statusCode:" + statusCode);
				System.out.println("Response content length: " + entity.getContentLength());
				if (statusCode == 200) {
					responseContent = EntityUtils.toString(entity, ENCODING);
				} else {
					responseContent = "请求状态异常：" + response.getStatusLine().getStatusCode();
					if (statusCode == 302) {
						responseContent += ";Redirect地址：" + response.getHeaders("Location");
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseContent;
	}

	public static void doDownload(String apiUrl, File file) {
		CloseableHttpClient httpClient = getHttpClient(apiUrl);
		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet(apiUrl);
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();

			writeStream(stream, file);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 发送 SSL POST 请求（HTTPS），K-V形式
	 *
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return
	 */
	public static String doPostSSL(String apiUrl, Map<String, Object> params) {
		CloseableHttpClient httpClient = getHttpClient(HttpProtocol.HTTPS);
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;

		try {
			// httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * 发送 SSL POST 请求（HTTPS），JSON形式
	 *
	 * @param apiUrl
	 *            API接口URL
	 * @param json
	 *            JSON对象
	 * @return
	 */
	public static String doPostSSL(String apiUrl, Object json) {
		CloseableHttpClient httpClient = getHttpClient(HttpProtocol.HTTPS);
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;

		try {
			// httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json.toString(), ENCODING);// 解决中文乱码问题
			stringEntity.setContentEncoding(ENCODING);
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	public static String doXmlSSL(String apiUrl, String xml) {
		CloseableHttpClient httpClient = getHttpClient(HttpProtocol.HTTPS);
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;

		try {
			StringEntity stringEntity = new StringEntity(xml, ENCODING);// 解决中文乱码问题
			stringEntity.setContentEncoding(ENCODING);
			stringEntity.setContentType("application/xml");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, ENCODING);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	public static void doDownloadSSL(String apiUrl, String data, String contentType, File file) {
		CloseableHttpClient httpClient = getHttpClient(HttpProtocol.HTTPS);
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;

		try {
			StringEntity stringEntity = new StringEntity(data, ENCODING);// 解决中文乱码问题
			stringEntity.setContentEncoding(ENCODING);
			stringEntity.setContentType(contentType);
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();

			writeStream(stream, file);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void writeStream(InputStream stream, File file) throws IOException {
		try {
			FileOutputStream out = new FileOutputStream(file);

			byte[] buff = new byte[2048];
			int length = 0;
			while ((length = stream.read(buff)) > 0) {
				out.write(buff, 0, length);
				// 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试
			}
			out.flush();
			out.close();
		} finally {
			stream.close(); // 关闭低层流。
		}
	}

//	private static CloseableHttpClient getHttpClient() {
//		return HttpClients.custom().setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
//	}

	private static CloseableHttpClient getHttpClient(String url) {
		HttpProtocol protocol = HttpProtocol.match(url);
		return getHttpClient(protocol);
	}

	private static CloseableHttpClient getHttpClient(HttpProtocol protocol) {
		HttpClientBuilder builder = HttpClients.custom().setConnectionManager(connMgr)
				.setDefaultRequestConfig(requestConfig);
		if (HttpProtocol.HTTPS.equals(protocol)) {
			builder.setSSLSocketFactory(createSSLSocketFactory());

		}
		return builder.build();
	}

	/**
	 * 创建SSL安全连接
	 *
	 * @return
	 */
	private static SSLConnectionSocketFactory createSSLSocketFactory() {
		try {

			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			}).build();

			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,
					NoopHostnameVerifier.INSTANCE);
//			new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null, NoopHostnameVerifier.INSTANCE);

			return socketFactory;
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			throw new IllegalStateException("Unexpected exception while building the certificate-ignoring SSLContext.",
					e);
		}
	}

	/**
	 * 测试方法
	 *
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(HttpUtils
				.doGet("http://xjcainfo.miitbeian.gov.cn/state/outPortal/queryLatestMessageInfo.action?id=21"));

	}
}
