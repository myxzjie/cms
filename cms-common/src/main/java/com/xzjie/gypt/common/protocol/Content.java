/**
 * radp-cms
 * @Title: Content.java 
 * @Package com.xzjie.gypt.common.protocol
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年8月31日
 */
package com.xzjie.gypt.common.protocol;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.xzjie.gypt.common.page.Page;

/**
 * @className Content.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月31日 下午10:04:45 
 * @version V0.0.1 
 */

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class Content implements Serializable{
	@XmlElement(name = "page")
	private Page page;
	
	@XmlElement(name = "message")
	private Object message;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
}
