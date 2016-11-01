/**
 * ask_rear
 * @Title: ExampleModel.java 
 * @Package com.ask.rear.api.model
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年1月29日
 */
package com.xzjie.gypt.api.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @className ExampleModel
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年1月29日 下午2:58:44
 * @version V0.0.1
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "example")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExampleModel implements Serializable {

	Integer id;
	String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
