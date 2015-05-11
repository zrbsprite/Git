/**
 * File Name:UserService.java
 * @Description: 
 * Copyright 2015 EASE Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月30日下午3:51:49
 */
package com.jsprite.web.service;

import java.util.Set;

import com.jsprite.core.IService;
import com.jsprite.web.model.UserModel;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月30日下午3:51:49 <br>
 * E-mail:  <br> 
 */
public interface UserService extends IService<UserModel>{
	
	public UserModel findUser(UserModel user);
	
	public Set<String> getUserRoles(UserModel user);

	public Set<String> getUserPermission(String username);
}
