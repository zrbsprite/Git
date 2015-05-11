/**
 * File Name:UserModel.java
 * @Description: 
 * Copyright 2015 EASE Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月30日上午9:34:03
 */
package com.jsprite.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月30日上午9:34:03 <br>
 * E-mail:  <br> 
 */
@Entity
@Table(name="ease_user")
public class UserModel {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="salt")
	private String salt;
	
	@Column(name="user_status")
	private String userStatus;
	
	@Transient
	private boolean rememberMe = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}
