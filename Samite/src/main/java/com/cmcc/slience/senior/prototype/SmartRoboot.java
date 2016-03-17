package com.cmcc.slience.senior.prototype;

import java.util.ArrayList;

public class SmartRoboot implements Cloneable {

	private ArrayList<String> list = new ArrayList<String>();

	public ArrayList<String> getList() {
		return list;
	}

	public void setValue(String value){
		list.add(value);
	}
	
	@Override
	public SmartRoboot clone() {
		SmartRoboot roboot = null;
		try {
			roboot = (SmartRoboot) super.clone();
			//…Ó∂»øΩ±¥
			roboot.list = (ArrayList<String>) roboot.list.clone();
		} catch (Exception e) {
			//ignore
		}
		return roboot;
	}
}
