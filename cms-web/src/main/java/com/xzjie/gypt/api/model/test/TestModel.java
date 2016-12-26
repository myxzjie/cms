package com.xzjie.gypt.api.model.test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

//@SuppressWarnings("serial")
//@XmlRootElement(name = "tests")
//@XmlAccessorType(XmlAccessType.FIELD)

@XStreamAlias("TestModel")
public class TestModel implements Serializable {

	private Integer id;
	private String name;
	private Date time; 
	
	private List<TestModel> models;

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

	public List<TestModel> getModels() {
		return models;
	}

	public void setModels(List<TestModel> models) {
		this.models = models;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
