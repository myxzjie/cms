package com.xzjie.mybatis.page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class PageEntity<T extends Object> implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5599983821736637144L;

	private Page page;

	private T t;

	private Map<String,Object> map;

	private List<T> rows;
	
	public PageEntity(){}
	
	public PageEntity(Page page,T t){
		this.page=page;
		this.t=t;
	}
	public PageEntity(Page page,T t,Map<String,Object> map){
		this.page=page;
		this.t=t;
		this.map=map;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}


	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
