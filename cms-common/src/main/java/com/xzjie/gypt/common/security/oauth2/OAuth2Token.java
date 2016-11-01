/**
 * radp-cms
 * @Title: OAuth2Token.java 
 * @Package com.xzjie.gypt.common.security.oauth2
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月31日
 */
package com.xzjie.gypt.common.security.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @className OAuth2Token.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月31日 下午6:33:11 
 * @version V0.0.1 
 */
@SuppressWarnings("serial")
public class OAuth2Token implements AuthenticationToken  {

	private String authCode;
    private String principal;

	public OAuth2Token(String authCode) {
        this.authCode = authCode;
    }
    
    @Override
    public Object getCredentials() {
        return authCode;
    }
    
	public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

}
