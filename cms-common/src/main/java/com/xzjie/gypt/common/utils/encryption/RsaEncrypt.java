package com.xzjie.gypt.common.utils.encryption;
/**
 * RSA安全编码组件
 * @author user
 *
 */
import java.security.Key;  
import java.security.KeyFactory;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.PrivateKey;  
import java.security.PublicKey;  
import java.security.Signature;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
import java.security.spec.PKCS8EncodedKeySpec;  
import java.security.spec.X509EncodedKeySpec;  
import java.util.HashMap;  
import java.util.Map;  
import javax.crypto.Cipher;
/**
 * 简要总结一下，使用公钥加密、私钥解密，完成了乙方到甲方的一次数据传递，通过私钥加密、公钥解密，
 * 同时通过私钥签名、公钥验证签名，完成了一次甲方到乙方的数据传递与验证，两次数据传递完成一整套的数据交互！ 

       类似数字签名，数字信封是这样描述的： 

      数字信封 
　　数字信封用加密技术来保证只有特定的收信人才能阅读信的内容。 
    流程： 
            信息发送方采用对称密钥来加密信息，然后再用接收方的公钥来加密此对称密钥（这部分称为数字信封），
            再将它和信息一起发送给接收方；接收方先用相应的私钥打开数字信封，得到对称密钥，然后使用对称密钥再解开信息。
 * 
 *
 *提供公钥的甲方保留私钥。举个例子，你在taobao买东西，你就是乙方，会用到安全证书，
 *证书是taobao给你发的，但是私钥是taobao自己留着的。
 */
public class RsaEncrypt extends Coder {

	public static final String KEY_ALGORITHM = "RSA";  
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  

	private static final String PUBLIC_KEY = "RSAPublicKey";  
	private static final String PRIVATE_KEY = "RSAPrivateKey";  
	
	public static final int KEY_SIZE = 512; 

	/** 
	 * 用私钥对信息生成数字签名 
	 *  
	 * @param data 
	 *            加密数据 
	 * @param privateKey 
	 *            私钥 
	 *  
	 * @return 
	 * @throws Exception 
	 */  
	public static String sign(byte[] data, String privateKey) throws Exception {  
	    // 解密由base64编码的私钥  
	    byte[] keyBytes = decryptBASE64(privateKey);  

	    // 构造PKCS8EncodedKeySpec对象  
	    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  

	    // KEY_ALGORITHM 指定的加密算法  
	    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  

	    // 取私钥匙对象  
	    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);  

	    // 用私钥对信息生成数字签名  
	    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
	    signature.initSign(priKey);  
	    signature.update(data);  

	    return encryptBASE64(signature.sign());  
	}  

	/** 
	 * 校验数字签名 
	 *  
	 * @param data 
	 *            加密数据 
	 * @param publicKey 
	 *            公钥 
	 * @param sign 
	 *            数字签名 
	 *  
	 * @return 校验成功返回true 失败返回false 
	 * @throws Exception 
	 *  
	 */  
	public static boolean verify(byte[] data, String publicKey, String sign)  
	        throws Exception {  

	    // 解密由base64编码的公钥  
	    byte[] keyBytes = decryptBASE64(publicKey);  

	    // 构造X509EncodedKeySpec对象  
	    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  

	    // KEY_ALGORITHM 指定的加密算法  
	    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  

	    // 取公钥匙对象  
	    PublicKey pubKey = keyFactory.generatePublic(keySpec);  

	    Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);  
	    signature.initVerify(pubKey);  
	    signature.update(data);  

	    // 验证签名是否正常  
	    return signature.verify(decryptBASE64(sign));  
	}  

	 /** 
	  * 解密<br> 
	  * 用私钥解密 
	  *  
	  * @param data 
	  * @param key 
	  * @return 
	  * @throws Exception 
	  */  
	 public static byte[] decryptByPrivateKey(byte[] data, String key)  
	         throws Exception {  
	     // 对密钥解密  
	     byte[] keyBytes = decryptBASE64(key);  

	     // 取得私钥  
	     PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
	     KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	     Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  

	     // 对数据解密  
	     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	     cipher.init(Cipher.DECRYPT_MODE, privateKey);  

	     return cipher.doFinal(data);  
	 }  

	 /** 
	  * 解密<br> 
	  * 用公钥解密 
	  *  
	  * @param data 
	  * @param key 
	  * @return 
	  * @throws Exception 
	  */  
	 public static byte[] decryptByPublicKey(byte[] data, String key)  
	         throws Exception {  
	     // 对密钥解密  
	     byte[] keyBytes = decryptBASE64(key);  

	     // 取得公钥  
	     X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
	     KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	     Key publicKey = keyFactory.generatePublic(x509KeySpec);  

	     // 对数据解密  
	     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	     cipher.init(Cipher.DECRYPT_MODE, publicKey);  

	     return cipher.doFinal(data);  
	 }  

	 /** 
	  * 加密<br> 
	  * 用公钥加密 
	  *  
	  * @param data 
	  * @param key 
	  * @return 
	  * @throws Exception 
	  */  
	 public static byte[] encryptByPublicKey(byte[] data, String key)  
	         throws Exception {  
	     // 对公钥解密  
	     byte[] keyBytes = decryptBASE64(key);  

	     // 取得公钥  
	     X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
	     KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	     Key publicKey = keyFactory.generatePublic(x509KeySpec);  

	     // 对数据加密  
	     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	     cipher.init(Cipher.ENCRYPT_MODE, publicKey);  

	     return cipher.doFinal(data);  
	 }  

	 /** 
	  * 加密<br> 
	  * 用私钥加密 
	  *  
	  * @param data 
	  * @param key 
	  * @return 
	  * @throws Exception 
	  */  
	 public static byte[] encryptByPrivateKey(byte[] data, String key)  
	         throws Exception {  
	     // 对密钥解密  
	     byte[] keyBytes = decryptBASE64(key);  

	     // 取得私钥  
	     PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
	     KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);  
	     Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  

	     // 对数据加密  
	     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
	     cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

	     return cipher.doFinal(data);  
	 }  

	 /** 
	  * 取得私钥 
	  *  
	  * @param keyMap 
	  * @return 
	  * @throws Exception 
	  */  
	 public static String getPrivateKey(Map<String, Object> keyMap)  
	         throws Exception {  
	     Key key = (Key) keyMap.get(PRIVATE_KEY);  

	     return encryptBASE64(key.getEncoded());  
	 }  

	 /** 
	  * 取得公钥 
	  *  
	  * @param keyMap 
	  * @return 
	  * @throws Exception 
	  */  
	 public static String getPublicKey(Map<String, Object> keyMap)  
	         throws Exception {  
	     Key key = (Key) keyMap.get(PUBLIC_KEY);  

	     return encryptBASE64(key.getEncoded());  
	 }  

	 /** 
	  * 初始化密钥 
	  *  
	  * @return 
	  * @throws Exception 
	  */  
	 public static Map<String, Object> initKey() throws Exception {  
	     KeyPairGenerator keyPairGen = KeyPairGenerator  
	             .getInstance(KEY_ALGORITHM);  
	     keyPairGen.initialize(KEY_SIZE);  

	     KeyPair keyPair = keyPairGen.generateKeyPair();  

	     // 公钥  
	     RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  

	     // 私钥  
	     RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  

	     Map<String, Object> keyMap = new HashMap<String, Object>(2);  

	     keyMap.put(PUBLIC_KEY, publicKey);  
	     keyMap.put(PRIVATE_KEY, privateKey);  
	     return keyMap;  
	}
}
