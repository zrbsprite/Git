package com.cmcc.slience.test.entity;

import com.google.gson.annotations.Expose;

public class Dog {

	@Expose
	private String name;
	
	@Expose
	private String categry;
	
	@Expose(deserialize=false,serialize=false)//��ʾ�����л�ʱ�����Ը����ԣ����л����Ը�����
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategry() {
		return categry;
	}

	public void setCategry(String categry) {
		this.categry = categry;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
