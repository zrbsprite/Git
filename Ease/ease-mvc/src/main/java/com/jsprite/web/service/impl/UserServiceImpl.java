/**
 * File Name:UserServiceImpl.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月30日下午3:57:17
 */
package com.jsprite.web.service.impl;

import javax.annotation.Resource;

import com.jsprite.core.BaseService;
import com.jsprite.web.dao.UserDao;
import com.jsprite.web.model.UserModel;
import com.jsprite.web.service.UserService;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月30日下午3:57:17 <br>
 * E-mail:  <br> 
 */
public class UserServiceImpl extends BaseService<UserModel> implements UserService {

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**方法名称：findUser <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年4月30日下午4:08:09 
	 * @see com.jsprite.web.service.UserService#findUser(com.jsprite.web.model.UserModel) 
	 * @param user
	 * @return
	 */
	@Override
	public UserModel findUser(UserModel user) {
		return this.userDao.findUser(user);
	}
}
