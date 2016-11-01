package com.xzjie.gypt.common.utils;

public enum Language {

	CODE_UTF_8("UTF-8",1),
	CODE_GBK("GBK",2);
	
	private String name;
	private int index;
	
	// 构造方法
	private Language(String name,int index){
		this.name=name;
		this.index=index;
	}
 
	// 覆盖方法
    @Override
    public String toString() {
        return this.name;
    }
    
    public int key(){
    	return this.index;
    }
	
}
