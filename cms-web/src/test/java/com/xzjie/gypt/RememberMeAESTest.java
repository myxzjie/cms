/**
 * radp-cms
 * @Title: RememberMeAESTest.java 
 * @Package com.xzjie.gypt
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月20日
 */
package com.xzjie.gypt;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.shiro.codec.Base64;
import org.junit.Test;

/**
 * @className RememberMeAESTest.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年6月20日 下午9:31:32 
 * @version V0.0.1 
 */
public class RememberMeAESTest {

	@Test
	public void rmemberTest() throws NoSuchAlgorithmException{
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecretKey deskey = keygen.generateKey();System.out.println(Base64.encodeToString(deskey.getEncoded()));
		

	}
}
