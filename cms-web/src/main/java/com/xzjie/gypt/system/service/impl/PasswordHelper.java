/**
 * 
 */
package com.xzjie.gypt.system.service.impl;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.system.model.Account;

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
}
