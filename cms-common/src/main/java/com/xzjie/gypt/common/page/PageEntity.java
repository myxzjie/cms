package com.xzjie.gypt.common.page;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PageEntity<T extends Object> implements Serializable {

	
	private Page page;

	private T record;
	
	public PageEntity(){
		
	}
	
	public PageEntity(Page page,T record){
		this.page=page;
		this.setRecord(record);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

	

}
