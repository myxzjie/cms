/**
 * radp-cms
 * @Title: WebPageSource.java 
 * @Package com.xzjie.gypt
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月18日
 */
package com.xzjie.gypt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @className WebPageSource.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月18日 下午4:26:32 
 * @version V0.0.1 
 */
public class WebPageSource {

	 public static void main(String args[]){    
	        URL url;
	        int responsecode;
	        HttpURLConnection urlConnection;
	        BufferedReader reader;
	        String line;
	        try{
	            //生成一个URL对象，要获取源代码的网页地址为：http://www.sina.com.cn
	            url=new URL("http://www.tianyancha.com/resources/views/route/mainV3.html");
	            //打开URL
	            urlConnection = (HttpURLConnection)url.openConnection();
	            //获取服务器响应代码
	            responsecode=urlConnection.getResponseCode();
	            if(responsecode==200){
	                //得到输入流，即获得了网页的内容 
	                reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
	                while((line=reader.readLine())!=null){
	                    System.out.println(line);
	                }
	            }
	            else{
	                System.out.println("获取不到网页的源码，服务器响应代码为："+responsecode);
	            }
	        }
	        catch(Exception e){
	            System.out.println("获取不到网页的源码,出现异常："+e);
	        }
	    }
}
