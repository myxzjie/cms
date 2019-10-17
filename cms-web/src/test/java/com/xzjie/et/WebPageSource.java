/**
 * radp-cms
 *
 * @Title: WebPageSource.java
 * @Package com.xzjie.gypt
 * @Description: TODO(添加描述)
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月18日
 */
package com.xzjie.et;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xzjie
 * @version V0.0.1
 * @className WebPageSource.java
 * @description TODO(添加描述)
 * @create 2016年8月18日 下午4:26:32
 */
public class WebPageSource {

    public static void main(String args[]) {
        URL url;
        int responsecode;
        HttpURLConnection urlConnection;
        BufferedReader reader;
        String line;
        try {
            //生成一个URL对象，要获取源代码的网页地址为：http://www.sina.com.cn
            url = new URL("http://www.tianyancha.com/resources/views/route/mainV3.html");
            //打开URL
            urlConnection = (HttpURLConnection) url.openConnection();
            //获取服务器响应代码
            responsecode = urlConnection.getResponseCode();
            if (responsecode == 200) {
                //得到输入流，即获得了网页的内容
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } else {
                System.out.println("获取不到网页的源码，服务器响应代码为：" + responsecode);
            }
        } catch (Exception e) {
            System.out.println("获取不到网页的源码,出现异常：" + e);
        }
    }


    @Test
    public void hsah() throws NoSuchAlgorithmException {
        byte[] salt = "demo5909af55d288d8f2581f7d572f2eb6bb".getBytes(); //Base64.encodeToString("123456".getBytes()).getBytes();
        byte[] bytes = "123456".getBytes(); //Base64.encodeToString("demo5909af55d288d8f2581f7d572f2eb6bb".getBytes()).getBytes();
        int hashIterations = 2;
        MessageDigest digest = MessageDigest.getInstance("MD5");
        if (salt != null) {
            digest.reset();
            digest.update(salt);
        }

        byte[] hashed = digest.digest(bytes);
        int iterations = hashIterations - 1;

//        for (int i = 0; i < iterations; ++i) {
//            digest.reset();
//            hashed = digest.digest(hashed);
//        }

        System.out.println(Hex.encodeToString(hashed));
    }

    @Test
    public void hsah2() throws NoSuchAlgorithmException {
        byte[] bytes = "demo5909af55d288d8f2581f7d572f2eb6bb123456".getBytes();
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(bytes);
        byte[] hashed = digest.digest();

        System.out.println(Hex.encodeToString(hashed));
    }


    @Test
    public void hsah3() throws NoSuchAlgorithmException {
        byte[] salt = "123456".getBytes();
        int hashIterations = 2;
        MessageDigest digest = MessageDigest.getInstance("MD5");
        if (salt != null) {
            digest.reset();
            digest.update(salt);
        }

        byte[] hashed = digest.digest();
//        int iterations = hashIterations - 1;
//
//        for (int i = 0; i < iterations; ++i) {
//            digest.reset();
//            hashed = digest.digest(hashed);
//        }

        System.out.println(Hex.encodeToString(hashed));
    }

    @Test
    public void SimpleHash() throws NoSuchAlgorithmException {
        String salt = "5909af55d288d8f2581f7d572f2eb6bb"; //new SecureRandomNumberGenerator().nextBytes().toHex();
        String newPassword = new MySimpleHash(
                "MD5",
                "123456",
                ByteSource.Util.bytes("demo" + salt),
                2).toString();


        System.out.println(salt);
        System.out.println(newPassword);
    }
}
