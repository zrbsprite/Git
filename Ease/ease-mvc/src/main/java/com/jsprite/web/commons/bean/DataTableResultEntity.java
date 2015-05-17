package com.jsprite.web.commons.bean;

import java.util.List;

/**
 * 描述：jQuery dataTable 结果集实体类<br>
 * 作者：ruibo <br>
 * 修改日期：2015年5月17日下午5:49:35 <br>
 * E-mail:  <br>
 */
public class DataTableResultEntity {

	private int sEcho;
	
	//总记录数
	private int iTotalRecords;
	
	//经过过滤后的总记录数
	private int iTotalDisplayRecords;
	
	//数组形式的数据结构的结果集
	private List<Object[]> aaData;

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public List<Object[]> getAaData() {
		return aaData;
	}

	public void setAaData(List<Object[]> aaData) {
		this.aaData = aaData;
	}
}