package com.cmcc.slience.test.prototype;

import com.cmcc.slience.senior.prototype.Roboot;

public class TestRoboot {

	
	public static void main(String[] args) {
		Roboot roboot = new Roboot("zhangsan");
		Roboot cloneRoboot = roboot.clone();
		System.out.println(cloneRoboot.getName());
	}
}
