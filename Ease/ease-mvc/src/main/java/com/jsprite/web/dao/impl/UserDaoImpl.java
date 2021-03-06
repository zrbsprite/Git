/**
 * File Name:UserDaoImpl.java
 * @Description: 
 * Copyright 2015 EASE Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月30日下午3:55:18
 */
package com.jsprite.web.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.jsprite.core.BaseDao;
import com.jsprite.core.utils.LogUtils;
import com.jsprite.web.dao.UserDao;
import com.jsprite.web.model.UserModel;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月30日下午3:55:18 <br>
 * E-mail:  <br> 
 */
@Repository("userDao")
@SuppressWarnings("unchecked")
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
		//criteria应该使用model的属性而不是数据库字段名
		if(!StringUtils.isEmpty(model.getUserName())){
			criteria.add(Restrictions.eq("userName", model.getUserName()));
		}
		if(model.getUserStatus()!=null){
			criteria.add(Restrictions.eq("userStatus", model.getUserStatus()));
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
	@Override
	public UserModel findUser(UserModel user) {
		DetachedCriteria criteria = DetachedCriteria.forClass(user.getClass());
		fillCriteria(criteria, user);
		try {
			List<UserModel> list = (List<UserModel>) this.getHibernateTemplate().findByCriteria(criteria);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			LogUtils.warn(e.getMessage(), getClass());
		}
		return null;
	}

	@Override
	public Set<String> getUserRoles(final UserModel user) {
		final String queryString = "select t2.role from ease_user t1 left join ease_user_role t3 on t1.id=t3.user_id left join ease_role t2 on t2.id=t3.role_id where t1.username=?";
		List<String> list = getHibernateTemplate().execute(new HibernateCallback<List<String>>() {
			@Override
			public List<String> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(queryString);
				query.setString(1, user.getUserName());
				return query.list();
			}
		});
		if(list!=null&&list.size()>0){
			Set<String> set =  new HashSet<String>();
			set.addAll(list);
			return set;
		}
		return null;
	}

	@Override
	public Set<String> getUserPermission(String username) {
		return null;
	}

}
