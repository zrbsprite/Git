/**
 * File Name:DataTargetMainModel.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年1月26日上午11:17:42
 */
package com.penzias.datatransfer.model;

import java.util.Date;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年1月26日上午11:17:42 <br>
 * E-mail: <br>
 */
public class DataTargetMainModel {

	private Integer itemId;

	private Integer subjectId;

	private Integer typeId;

	private String itemContent;

	private String itemImage;

	private Integer creatorId;

	private Date createDt;

	private Date version;

	private Float pValue;

	private Float tValue;

	private Integer pumpTimes;

	private Date lastedPumpdate;

	private String correctKey;

	private Integer statusId;

	private String selectedContent;

	private Integer testTimes;

	private Integer testCorrectTimes;

	private Character isLock;

	private Date lockDt;

	private Character havePatient;

	private String itemSource;

	private String pointsClassify;

	private String cogntiveLevel;

	private String itemSolution;

	private String itemFlag;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public Float getpValue() {
		return pValue;
	}

	public void setpValue(Float pValue) {
		this.pValue = pValue;
	}

	public Float gettValue() {
		return tValue;
	}

	public void settValue(Float tValue) {
		this.tValue = tValue;
	}

	public Integer getPumpTimes() {
		return pumpTimes;
	}

	public void setPumpTimes(Integer pumpTimes) {
		this.pumpTimes = pumpTimes;
	}

	public Date getLastedPumpdate() {
		return lastedPumpdate;
	}

	public void setLastedPumpdate(Date lastedPumpdate) {
		this.lastedPumpdate = lastedPumpdate;
	}

	public String getCorrectKey() {
		return correctKey;
	}

	public void setCorrectKey(String correctKey) {
		this.correctKey = correctKey;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getSelectedContent() {
		return selectedContent;
	}

	public void setSelectedContent(String selectedContent) {
		this.selectedContent = selectedContent;
	}

	public Integer getTestTimes() {
		return testTimes;
	}

	public void setTestTimes(Integer testTimes) {
		this.testTimes = testTimes;
	}

	public Integer getTestCorrectTimes() {
		return testCorrectTimes;
	}

	public void setTestCorrectTimes(Integer testCorrectTimes) {
		this.testCorrectTimes = testCorrectTimes;
	}

	public Date getLockDt() {
		return lockDt;
	}

	public void setLockDt(Date lockDt) {
		this.lockDt = lockDt;
	}

	public String getItemSource() {
		return itemSource;
	}

	public void setItemSource(String itemSource) {
		this.itemSource = itemSource;
	}

	public String getPointsClassify() {
		return pointsClassify;
	}

	public void setPointsClassify(String pointsClassify) {
		this.pointsClassify = pointsClassify;
	}

	public String getCogntiveLevel() {
		return cogntiveLevel;
	}

	public void setCogntiveLevel(String cogntiveLevel) {
		this.cogntiveLevel = cogntiveLevel;
	}

	public String getItemSolution() {
		return itemSolution;
	}

	public void setItemSolution(String itemSolution) {
		this.itemSolution = itemSolution;
	}

	public String getItemFlag() {
		return itemFlag;
	}

	public void setItemFlag(String itemFlag) {
		this.itemFlag = itemFlag;
	}

	public Character getIsLock() {
		return isLock;
	}

	public void setIsLock(Character isLock) {
		this.isLock = isLock;
	}

	public Character getHavePatient() {
		return havePatient;
	}

	public void setHavePatient(Character havePatient) {
		this.havePatient = havePatient;
	}
}
