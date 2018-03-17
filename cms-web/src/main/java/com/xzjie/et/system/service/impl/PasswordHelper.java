/**
 * 
 */
package com.xzjie.et.system.service.impl;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xzjie.et.system.model.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * @className PasswordHelper.java
 * @description TODO
 * @author xzjie
 * @create 2016年6月11日 下午12:19:42 
 * @version V0.0.1
 */
@Service
public class PasswordHelper {

    char[] str = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '~', '!', '@', '#', '$', '%', '^', '-', '+'
    };

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private String algorithmName = "md5";
    @Value("${password.hashIterations}")
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(Account account) {

    	account.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                account.getPassword(),
                ByteSource.Util.bytes(account.getCredentialsSalt()),
                hashIterations).toHex();

        account.setPassword(newPassword);
    }
    
    /**
     * 
     * @param account 输入 name , salt, password(明文)
     * @param password 加密的
     * @return
     */
    public boolean verifyPassword(Account account,String password){
    	
    	String newPassword = new SimpleHash(
                algorithmName,
                account.getPassword(),
                ByteSource.Util.bytes(account.getCredentialsSalt()),
                hashIterations).toHex();
    	
    	return newPassword.equals(password);
    }

    public String  getPassWord(int len){
        int i;  //生成的随机数
        int count = 0; //生成的密码的长度
        StringBuffer stringBuffer = new StringBuffer("");
        List<String> list = new ArrayList<String>();
        Random r = new Random();
        for (i = 0; i<str.length; i++) {
            list.add(str[i] + "");
        }
        Collections.shuffle(list);
        while(count < len){
            //生成 0 ~ 密码字典-1之间的随机数
            i = r.nextInt(list.size());
            stringBuffer.append(list.get(i));
            count ++;
        }
        return stringBuffer.toString();
    }
}
