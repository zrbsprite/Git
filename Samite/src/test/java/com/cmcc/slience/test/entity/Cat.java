package com.cmcc.slience.test.entity;

public class Cat {

	private String name;
	
	private String type;

	public Cat(String name,String type){
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return name+"++++"+type;
	}
	
	public Cat(){}
}
