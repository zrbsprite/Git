package com.jsprite.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ease_role")
public class RoleModel {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String role;
	
	private String description;
	
	private Integer avaliable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(Integer avaliable) {
		this.avaliable = avaliable;
	}
}
