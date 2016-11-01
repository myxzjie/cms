/**
 * radp-cms
 * @Title: OAuth2AuthenticationException.java 
 * @Package com.xzjie.gypt.common.security.oauth2
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月31日
 */
package com.xzjie.gypt.common.security.oauth2;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @className OAuth2AuthenticationException.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年7月31日 下午6:26:03 
 * @version V0.0.1 
 */
@SuppressWarnings("serial")
public class OAuth2AuthenticationException extends AuthenticationException {

    public OAuth2AuthenticationException(Throwable cause) {
        super(cause);
    }
}
