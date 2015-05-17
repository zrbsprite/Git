package com.jsprite.web.commons.bean;

/**
 * 描述：jQuery dataTable分页数据参数实体类<br>
 * 作者：ruibo <br>
 * 修改日期：2015年5月17日下午5:49:01 <br>
 * E-mail:  <br>
 */
public class DataTableParams {

	//每次请求datatable都会发送这个参数，与分页有关系的按钮有关系
	private int sEcho;
	
	//记录开始的索引
	private int iDisplayStart;
	
	//记录的数量，相当于分页步长
	private int iDisplayLength;

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
}
