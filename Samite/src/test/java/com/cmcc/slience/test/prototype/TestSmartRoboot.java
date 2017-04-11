package com.cmcc.slience.test.prototype;

import com.cmcc.slience.senior.prototype.SmartRoboot;

public class TestSmartRoboot {

	public static void main(String[] args) {
		SmartRoboot roboot = new SmartRoboot();
		roboot.setValue("zhangsan");
		
		SmartRoboot cloneRoboot = roboot.clone();
		cloneRoboot.setValue("lisi");
		
		System.out.println(roboot.getList());
		System.out.println(cloneRoboot.getList());
	}
}
