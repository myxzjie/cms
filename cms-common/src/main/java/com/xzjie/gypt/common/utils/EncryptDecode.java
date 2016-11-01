/**
 * ask_rear
 * @Title: EncryptDecode.java 
 * @Package com.ask.rear.utils
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年2月19日
 */
package com.xzjie.gypt.common.utils;

import org.springframework.stereotype.Component;

import com.xzjie.gypt.common.utils.encryption.Coder;


/**
 * @className EncryptDecode
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年2月19日 上午11:14:11
 * @version V0.0.1 
 */
@Component
public final class EncryptDecode extends Coder {

	private static String PASSWORD = "PWD";
	//private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /*@Value("${password.algorithmName}")
    private static String algorithmName ;
    @Value("${password.hashIterations}")
    private static int hashIterations ;

    private final static String passwordKey="PASSWORD_KEY";
    private final static String saltKey="SALT_KEY";*/
	
	
	/**
	 * 对密码进行返回加密后密码，不可以逆转加密
	 * 
	 * @param password
	 *            明文密码
	 * @return 加密后密码
	 */
	public static String getPasswordEncrypt(String password) {
		String data=PASSWORD + password + PASSWORD;
		try {
			String base64 = encryptBASE64(data.getBytes());
			return MD5Encoder.getMD5(base64);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*public static Map<String, String> encryptPassword(String username,String password) {

		Map<String, String> map=new HashMap<String, String>();
        String salt=randomNumberGenerator.nextBytes().toHex();
       

        String newPassword = new SimpleHash(algorithmName,password,ByteSource.Util.bytes(username+salt),hashIterations).toHex();

        map.put(passwordKey, newPassword);
        map.put(saltKey, salt);
        
        return map;
    }*/
}
