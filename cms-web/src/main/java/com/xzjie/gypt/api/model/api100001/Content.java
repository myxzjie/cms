/**
 * radp-cms
 * @Title: Content.java 
 * @Package com.xzjie.gypt.api.model.resource
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月1日
 */
package com.xzjie.gypt.api.model.api100001;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.system.model.Resource;

/**
 * @className Content.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月1日 上午12:19:11 
 * @version V0.0.1 
 */
@XmlRootElement(name = "content")
@XmlAccessorType(XmlAccessType.FIELD)
public class Content {

	private Page page;
	
	private String ciphertext;
	
	@XmlElement(name = "resources")
	private List<Resource> resources;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public String getCiphertext() {
		return ciphertext;
	}
	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
}
