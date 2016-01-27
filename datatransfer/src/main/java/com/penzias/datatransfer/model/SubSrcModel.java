/**
 * File Name:SubSrcModel.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年1月27日下午2:49:53
 */
package com.penzias.datatransfer.model;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年1月27日下午2:49:53 <br>
 * E-mail: <br>
 */
public class SubSrcModel {

	private Integer examSubId;

	private Integer examId;

	private String examContent;

	private String trueSolu;

	private String examSolu;

	public Integer getExamSubId() {
		return examSubId;
	}

	public void setExamSubId(Integer examSubId) {
		this.examSubId = examSubId;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getExamContent() {
		return examContent;
	}

	public void setExamContent(String examContent) {
		this.examContent = examContent;
	}

	public String getTrueSolu() {
		return trueSolu;
	}

	public void setTrueSolu(String trueSolu) {
		this.trueSolu = trueSolu;
	}

	public String getExamSolu() {
		return examSolu;
	}

	public void setExamSolu(String examSolu) {
		this.examSolu = examSolu;
	}

}
