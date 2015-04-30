/**
 * File Name:UserDaoImpl.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月30日下午3:55:18
 */
package com.jsprite.web.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jsprite.core.BaseDao;
import com.jsprite.web.dao.UserDao;
import com.jsprite.web.model.UserModel;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月30日下午3:55:18 <br>
 * E-mail:  <br> 
 */
public class UserDaoImpl extends BaseDao<UserModel> implements UserDao {

	/**方法名称：fillCriteria <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年4月30日下午3:56:09 
	 * @see com.jsprite.core.BaseDao#fillCriteria(org.hibernate.criterion.DetachedCriteria, java.lang.Object) 
	 * @param criteria
	 * @param t
	 */
	@Override
	public void fillCriteria(DetachedCriteria criteria, UserModel model) {
		if(model.getId()!=null){
			criteria.add(Restrictions.eq("id", model.getId()));
		}
		if(!StringUtils.isEmpty(model.getUserName())){
			criteria.add(Restrictions.eq("username", model.getUserName()));
		}
		if(!StringUtils.isEmpty(model.getUserStatus())){
			criteria.add(Restrictions.eq("user_status", model.getUserStatus()));
		}
	}

	/**方法名称：findUser <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年4月30日下午4:09:24 
	 * @see com.jsprite.web.dao.UserDao#findUser(com.jsprite.web.model.UserModel) 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserModel findUser(UserModel user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(user.getClass());
		fillCriteria(criteria, user);
		List<UserModel> list = (List<UserModel>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
