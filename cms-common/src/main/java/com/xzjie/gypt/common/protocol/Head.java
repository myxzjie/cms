/**
 * radp-cms
 * @Title: Head.java 
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @className Head.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年8月31日 下午9:59:14 
 * @version V0.0.1 
 */
/*@XmlRootElement(name = "head")
@XmlAccessorType(XmlAccessType.FIELD)*/
public class Head implements Serializable{
	private String code;
	private String massage;
	private Boolean success;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
