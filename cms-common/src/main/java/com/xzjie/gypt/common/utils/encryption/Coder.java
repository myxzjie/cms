 package com.xzjie.gypt.common.utils.encryption;
 import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 基础加密组件
 * @author user
 *
 *BASE64的加密解密是双向的，可以求反解。 
    MD5、SHA以及HMAC是单向加密，任何数据加密后只会产生唯一的一个加密串，通常用来校验数据在传输过程中是否被修改。
    其中HMAC算法有一个密钥，增强了数据传输过程中的安全性，强化了算法外的不可控因素。 
    单向加密的用途主要是为了校验数据在传输过程中是否被修改。
 */
public abstract class Coder {

	 public static final String KEY_SHA = "SHA";  
	 public static final String KEY_MD5 = "MD5";  

	 /** 
	  * MAC算法可选以下多种算法 
	  *  
	  * <pre> 
	  * HmacMD5  
	  * HmacSHA1  
	  * HmacSHA256  
	  * HmacSHA384  
	  * HmacSHA512 
	  * </pre> 
	  */  
	 public static final String KEY_MAC = "HmacMD5";  

	 /** 
	  * BASE64解密 
	  *  
	  * @param key 
	  * @return 
	  * @throws Exception 
	  */  
	 public static byte[] decryptBASE64(String key) throws Exception {  
		
		 return Base64.decodeBase64(key);
	 }  

	 /** 
	  * BASE64加密 
	  *  
	  * @param key 
	  * @return 
	  * @throws Exception 
	  */  
	 public static String encryptBASE64(byte[] key) throws Exception {  
		 return Base64.encodeBase64String(key);
	 }  

	 /** 
	  * MD5加密 
	  *  
	  * @param data 
	  * @return 
	  * @throws Exception 
	  */  
	 public static byte[] encryptMD5(byte[] data) throws Exception {  

	     MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);  
	     md5.update(data);  

	     return md5.digest();  

	 }  

	 /** 
	  * SHA加密 
	  *  
	  * @param data 
	  * @return 
	  * @throws Exception 
	  */  
	 public static byte[] encryptSHA(byte[] data) throws Exception {  

	     MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
	     sha.update(data);  

	     return sha.digest();  

	 }  

	 /** 
	  * 初始化HMAC密钥 
	  *  
	  * @return 
	  * @throws Exception 
	  */  
	 public static String initMacKey() throws Exception {  
	     KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);  

	     SecretKey secretKey = keyGenerator.generateKey();  
	     return encryptBASE64(secretKey.getEncoded());  
	 }  

	  /** 
	   * HMAC加密 
	   *  
	   * @param data 
	   * @param key 
	   * @return 
	   * @throws Exception 
	   */  
	  public static byte[] encryptHMAC(byte[] data, String key) throws Exception {  

	      SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);  
	      Mac mac = Mac.getInstance(secretKey.getAlgorithm());  
	      mac.init(secretKey);  

	      return mac.doFinal(data);  

	  }  

}
