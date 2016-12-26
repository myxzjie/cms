/**
 * radp-cms
 * @Title: ResourceResponse.java 
 * @Package com.xzjie.gypt.api.model.resource
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月1日
 */
package com.xzjie.gypt.api.model.api10002;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xzjie.gypt.common.protocol.Head;

/**
 * @className ResourceResponse.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月1日 上午12:18:48 
 * @version V0.0.1 
 */
//@XmlRootElement(name = "response")
//@XmlAccessorType(XmlAccessType.FIELD)
@XStreamAlias("response")
public class Response implements Serializable{

	private Head head;
	
	private Content content;
	public Response(){}
	public Response(Head head,Content content){
		this.head=head;
		this.content=content;
	}

	public Head getHead() {
		return head;
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
