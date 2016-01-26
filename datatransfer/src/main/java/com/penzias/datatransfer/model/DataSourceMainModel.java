/**
 * File Name:DataSourceModel.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年1月25日上午11:27:30
 */
package com.penzias.datatransfer.model;

import java.util.Date;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年1月25日上午11:27:30 <br>
 * E-mail: <br>
 */
public class DataSourceMainModel {

	private Integer examid;

	private Integer examsubject;

	private Integer examtype;

	private Integer isauto;

	private Integer examdiff;

	private String examimage;

	private String examcontent;

	private String examcontent1;

	private String examsolu;

	private String truesolu;

	private Integer sontype;

	private String sonexamid;

	private Date examseldate;

	public Integer getExamid() {
		return examid;
	}

	public void setExamid(Integer examid) {
		this.examid = examid;
	}

	public Integer getExamsubject() {
		return examsubject;
	}

	public void setExamsubject(Integer examsubject) {
		this.examsubject = examsubject;
	}

	public Integer getExamtype() {
		return examtype;
	}

	public void setExamtype(Integer examtype) {
		this.examtype = examtype;
	}

	public Integer getIsauto() {
		return isauto;
	}

	public void setIsauto(Integer isauto) {
		this.isauto = isauto;
	}

	public Integer getExamdiff() {
		return examdiff;
	}

	public void setExamdiff(Integer examdiff) {
		this.examdiff = examdiff;
	}

	public String getExamimage() {
		return examimage;
	}

	public void setExamimage(String examimage) {
		this.examimage = examimage;
	}

	public String getExamcontent() {
		return examcontent;
	}

	public void setExamcontent(String examcontent) {
		this.examcontent = examcontent;
	}

	public String getExamcontent1() {
		return examcontent1;
	}

	public void setExamcontent1(String examcontent1) {
		this.examcontent1 = examcontent1;
	}

	public String getExamsolu() {
		return examsolu;
	}

	public void setExamsolu(String examsolu) {
		this.examsolu = examsolu;
	}

	public String getTruesolu() {
		return truesolu;
	}

	public void setTruesolu(String truesolu) {
		this.truesolu = truesolu;
	}

	public Integer getSontype() {
		return sontype;
	}

	public void setSontype(Integer sontype) {
		this.sontype = sontype;
	}

	public String getSonexamid() {
		return sonexamid;
	}

	public void setSonexamid(String sonexamid) {
		this.sonexamid = sonexamid;
	}

	public Date getExamseldate() {
		return examseldate;
	}

	public void setExamseldate(Date examseldate) {
		this.examseldate = examseldate;
	}
}
