/**
 * radp-cms
 * @Title: OAuthService.java 
 * @Package com.xzjie.gypt.system.service
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月2日
 */
package com.xzjie.gypt.system.service;

import java.util.Set;

/**
 * @className OAuthService.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月2日 下午11:44:24 
 * @version V0.0.1 
 */
public interface OAuthService {

	//添加 auth code
    void addAuthCode(String authCode, String username);
    
    //添加 access token
    void addAccessToken(String accessToken, String username);

    //验证auth code是否有效
    boolean checkAuthCode(String authCode);
    //验证access token是否有效
    boolean checkAccessToken(String accessToken);

    String getUsernameByAuthCode(String authCode);
    
    String getUsernameByAccessToken(String accessToken);


    //auth code / access token 过期时间
    long getExpireIn();


    boolean checkClientId(String clientId);

    boolean checkClientSecret(String clientSecret);
    
    Set<String> getUserRoles(Long orgId, Long userId);
    
    Set<String>  getPermissions(Long orgId, Long userId);
}
