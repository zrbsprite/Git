package com.jsprite.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jsprite.web.commons.inf.ITree;

@Entity
@Table(name="ease_menu")
public class Menu implements ITree{

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="menu_name")
	private String name;
	
	@Column(name="name_url")
	private String url;
	
	@Column(name="name_type")
	private Integer type;
	
	@Column(name="name_desc")
	private String desc;
	
	@Column(name="parentId")
	private String parentId;
	
	@Column(name="isParent")
	private String isParent;
	
	@Column(name="menu_status")
	private Integer status;
	
	@Column(name="menu_level")
	private Integer level;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
