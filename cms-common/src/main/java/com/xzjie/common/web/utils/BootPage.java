package com.xzjie.common.web.utils;

import java.util.List;

/**
 * Created by xzjie on 2017/7/9.
 */
public final class BootPage<T> {
    protected int total;
    protected List<T> rows;
    protected int limit=10;
    protected int offset = 0;
    protected String order ="asc" ;
    protected String searchText=null;
    protected String searchfield=null;
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getSearchText() {
        return searchText;
    }
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    public String getSearchfield() {
        return searchfield;
    }
    public void setSearchfield(String searchfield) {
        this.searchfield = searchfield;
    }
}
