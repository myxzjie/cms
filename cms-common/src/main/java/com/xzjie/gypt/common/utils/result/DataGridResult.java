package com.xzjie.gypt.common.utils.result;

import java.util.List;

public class DataGridResult<T> {

	private Integer total; // 总记录
	private List<T> rows; // 显示的记录
	
	public DataGridResult(){
		
	}
	
	public DataGridResult(List<T> rows, Integer total){
		this.rows=rows;
		this.total=total;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	/*
	 * public static DataGridResult dataGrid(Object list,Integer total){
	 * DataGridResult result=new DataGridResult(); result.setRows(list);
	 * result.setTotal(total); return result; }
	 */

}
