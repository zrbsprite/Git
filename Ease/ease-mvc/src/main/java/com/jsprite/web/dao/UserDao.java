/**
 * File Name:UserDao.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月30日下午3:54:38
 */
package com.jsprite.web.dao;

import java.util.Set;

import com.jsprite.core.IDao;
import com.jsprite.web.model.UserModel;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月30日下午3:54:38 <br>
 * E-mail:  <br> 
 */
public interface UserDao extends IDao<UserModel> {

	public UserModel findUser(UserModel user);

	public Set<String> getUserRoles(UserModel user);

	public Set<String> getUserPermission(String username);
}
