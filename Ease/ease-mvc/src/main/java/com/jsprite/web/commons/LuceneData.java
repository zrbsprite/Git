/**
 * File Name:LuceneData.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月7日下午4:10:06
 */
package com.jsprite.web.commons;

/**
 * 描述：lucene数据类<br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月7日下午4:10:06 <br>
 * E-mail: <br>
 */
public class LuceneData {
	private String id;
	private String name;
	private String author;
	private String imgPath;
	private String outline; // 描述
	private String type; // 类型
	private String typeid;// 类型 id
	private String bigtype; // 总类型

	private String updateTime;
	private String imgUrlPath;
	private String content;
	private String link_url;

	private Long hot = 0l;

	private Long clickPoint = 0l;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getImgUrlPath() {
		return imgUrlPath;
	}

	public void setImgUrlPath(String imgUrlPath) {
		this.imgUrlPath = imgUrlPath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String linkUrl) {
		link_url = linkUrl;
	}

	public Long getHot() {
		return hot;
	}

	public void setHot(Long hot) {
		this.hot = hot;
	}

	public Long getClickPoint() {
		return clickPoint;
	}

	public void setClickPoint(Long clickPoint) {
		this.clickPoint = clickPoint;
	}

	public String getBigtype() {
		return bigtype;
	}

	public void setBigtype(String bigtype) {
		this.bigtype = bigtype;
	}

	@Override
	public String toString() {
		return "LuceneData [author=" + author + ", bigtype=" + bigtype
				+ ", clickPoint=" + clickPoint + ", content=" + content
				+ ", hot=" + hot + ", id=" + id + ", imgPath=" + imgPath
				+ ", imgUrlPath=" + imgUrlPath + ", link_url=" + link_url
				+ ", name=" + name + ", outline=" + outline + ", type=" + type
				+ ", updateTime=" + updateTime + "]";
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
}
