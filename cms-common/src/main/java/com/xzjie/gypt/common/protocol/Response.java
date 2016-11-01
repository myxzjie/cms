/**
 * radp-cms
 * @Title: Response.java 
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
 * @className Response.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月31日 下午9:57:44 
 * @version V0.0.1 
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response implements Serializable{

	private Head head;
	
	@XmlElement(name = "content")
	private Content content;

	public Head getHead() {
		return head;
	}
	
	public Response(){}
	
	public Response(Head head,Content content){
		this.head=head;
		this.content=content;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}
	
	
}
