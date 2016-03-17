package com.cmcc.slience.senior.prototype;

public class Roboot implements Cloneable{

	private String name;
	
	public Roboot(String name){
		this.name = name;
		System.out.println("构造函数被执行了……");
	}
	
	@Override
	public Roboot clone() {
		Roboot roboot = null;
		try {
			roboot = (Roboot) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roboot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
