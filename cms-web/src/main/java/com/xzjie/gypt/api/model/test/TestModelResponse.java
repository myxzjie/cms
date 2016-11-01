/**
 * ask_rear
 * @Title: TestModels.java 
 * @Package com.ask.rear.core.model
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年2月2日
 */
package com.xzjie.gypt.api.model.test;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.protocol.Head;

/**
 * @className TestModels
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年2月2日 下午5:17:06
 * @version V0.0.1
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestModelResponse {

	private Head head;
	private Page page;
	// @XmlElementWrapper(name="tests")
	//@XmlElement(name = "tests")
	private List<TestModel> items;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<TestModel> getItems() {
		return items;
	}

	public void setItems(List<TestModel> items) {
		this.items = items;
	}
}
