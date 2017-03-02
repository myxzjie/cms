package com.xzjie.gypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class TestTemp {

	/** 
     * @param args 
     */  
    public static void main(String[] args) {  
        String url = "http://data.zz.baidu.com/urls?site=www.dev56.com&token=xxxxx";//网站的服务器连接  
        String[] param = {  
                "http://www.dev56.com/f/index?cid=1",
                "http://www.dev56.com/f/category?cid=1&id=10",
                "http://www.dev56.com/f/category?cid=1&id=11",
                "http://www.dev56.com/f/category?cid=1&id=2",
                "http://www.dev56.com/f/category?cid=1&id=9",
                "http://www.dev56.com/f/category?cid=1&id=12",
                "http://www.dev56.com/f/category?cid=1&id=4",
                "http://www.dev56.com/f/category?cid=1&id=5",
                "http://www.dev56.com/f/category?cid=1&id=8",
                "http://www.dev56.com/f/category?cid=1&id=6","http://www.dev56.com/f/login?cid=1","http://www.dev56.com/f/register?cid=1","http://www.dev56.com/f/article?cid=1&id=40","http://www.dev56.com/f/article?cid=1&id=39","http://www.dev56.com/f/article?cid=1&id=38","http://www.dev56.com/f/article?cid=1&id=37","http://www.dev56.com/f/article?cid=1&id=36","http://www.dev56.com/f/article?cid=1&id=35","http://www.dev56.com/f/article?cid=1&id=34","http://www.dev56.com/f/article?cid=1&id=33","http://www.dev56.com/f/article?cid=1&id=32","http://www.dev56.com/f/article?cid=1&id=31","http://www.dev56.com/f/article?cid=1&id=26","http://www.dev56.com/f/article?cid=1&id=21","http://www.dev56.com/f/article?cid=1&id=18","http://www.dev56.com/f/article?cid=1&id=9","http://www.dev56.com/f/article?cid=1&id=16","http://www.dev56.com/f/article?cid=1&id=15","http://www.dev56.com/f/article?cid=1&id=14","http://www.dev56.com/f/article?cid=1&id=5","http://www.dev56.com/f/article?cid=1&id=20","http://www.dev56.com/f/article?cid=1&id=19","http://www.dev56.com/f/article?cid=1&id=25","http://www.dev56.com/f/article?cid=1&id=22","http://www.dev56.com/f/article?cid=1&id=1"//需要推送的网址  
        };  
        String json = Post(url, param);//执行推送方法  
        System.out.println("结果是"+json);  //打印推送结果  
  
    }  
      
    /** 
     * 百度链接实时推送 
     * @param PostUrl 
     * @param Parameters 
     * @return 
     */  
    public static String Post(String PostUrl,String[] Parameters){  
        if(null == PostUrl || null == Parameters || Parameters.length ==0){  
            return null;  
        }  
        String result="";  
        PrintWriter out=null;  
        BufferedReader in=null;  
        try {  
            //建立URL之间的连接  
            URLConnection conn=new URL(PostUrl).openConnection();  
            //设置通用的请求属性  
            conn.setRequestProperty("Host","data.zz.baidu.com");  
            conn.setRequestProperty("User-Agent", "curl/7.12.1");  
            conn.setRequestProperty("Content-Length", "83");  
            conn.setRequestProperty("Content-Type", "text/plain");  
               
            //发送POST请求必须设置如下两行  
            conn.setDoInput(true);  
            conn.setDoOutput(true);  
               
            //获取conn对应的输出流  
            out=new PrintWriter(conn.getOutputStream());  
            //发送请求参数  
            String param = "";  
            for(String s : Parameters){  
                param += s+"\n";  
            }  
            out.print(param.trim());  
            //进行输出流的缓冲  
            out.flush();  
            //通过BufferedReader输入流来读取Url的响应  
            in=new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while((line=in.readLine())!= null){  
                result += line;  
            }  
               
        } catch (Exception e) {  
            System.out.println("发送post请求出现异常！"+e);  
            e.printStackTrace();  
        } finally{  
            try{  
                if(out != null){  
                    out.close();  
                }  
                if(in!= null){  
                    in.close();  
                }  
                   
            }catch(IOException ex){  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
}
