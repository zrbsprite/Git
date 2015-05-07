/**
 * File Name:Novel.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月7日下午4:25:36
 */
package com.jsprite.web.commons;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月7日下午4:25:36 <br>
 * E-mail:  <br> 
 */
public class Novel {
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
	
	private NovelType novelType; 
	
	public Long getHot() {
		return hot;
	}
	public void setHot(Long hot) {
		this.hot = hot;
	}
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
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getBigtype() {
		return bigtype;
	}
	public void setBigtype(String bigtype) {
		this.bigtype = bigtype;
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
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	public Long getClickPoint() {
		return clickPoint;
	}
	public void setClickPoint(Long clickPoint) {
		this.clickPoint = clickPoint;
	}
	public NovelType getNovelType() {
		return novelType;
	}
	public void setNovelType(NovelType novelType) {
		this.novelType = novelType;
	}
}
