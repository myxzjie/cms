/**
 * radp-cms
 * @Title: FlexigridModel.java 
 * @Package com.xzjie.gypt.common.utils.result
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月6日
 */
package com.xzjie.gypt.common.utils.flexigrid;

import java.util.List;

/**
 * @className FlexigridModel.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年7月6日 下午10:09:34
 * @version V0.0.1
 */
public class FlexigridModel<T> {

	private int total; // 总数
	private int page; // 当前页
	private List<FlexigridRows<T>> rows; // rows对象有多个 所以使用list

	public FlexigridModel(int total, int page, List<FlexigridRows<T>> rows) {
		super();
		this.setTotal(total);
		this.setPage(page);
		this.setRows(rows);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<FlexigridRows<T>> getRows() {
		return rows;
	}

	public void setRows(List<FlexigridRows<T>> rows) {
		this.rows = rows;
	}
}
