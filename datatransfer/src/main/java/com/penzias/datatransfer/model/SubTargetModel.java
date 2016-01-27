/**
 * File Name:SubTargetModel.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年1月27日下午2:50:13
 */
package com.penzias.datatransfer.model;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年1月27日下午2:50:13 <br>
 * E-mail: <br>
 */
public class SubTargetModel {

	private Integer subitemId;

	private Integer itemId;

	private String subContent;

	private String correctKey;

	private String selectItem;

	public Integer getSubitemId() {
		return subitemId;
	}

	public void setSubitemId(Integer subitemId) {
		this.subitemId = subitemId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getSubContent() {
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public String getCorrectKey() {
		return correctKey;
	}

	public void setCorrectKey(String correctKey) {
		this.correctKey = correctKey;
	}

	public String getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(String selectItem) {
		this.selectItem = selectItem;
	}

}
