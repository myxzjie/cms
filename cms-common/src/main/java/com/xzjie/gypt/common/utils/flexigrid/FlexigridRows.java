/**
 * radp-cms
 * @Title: FlexigridRows.java 
 * @Package com.xzjie.gypt.common.utils.result
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月6日
 */
package com.xzjie.gypt.common.utils.flexigrid;

/**
 * @className FlexigridRows.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年7月6日 下午10:07:57
 * @version V0.0.1
 */
public class FlexigridRows<T> {

	private int id; // 每个rows里面的id
	private T cell; // 具体对应的对象

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public T getCell() {
		return cell;
	}

	public void setCell(T cell) {
		this.cell = cell;
	}

	public FlexigridRows(int id, T cell) {
		super();
		this.id = id;
		this.cell = cell;
	}

}
