package com.jsprite.web.commons;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.jsprite.core.Pagination;
import com.jsprite.core.data.Conditions;

public abstract class BasicDaoImpl<T> implements BaseDao<T> {

	private Logger logger = Logger.getLogger(getClass());
	
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public Serializable addEntity(T entity) {
		return this.hibernateTemplate.save(entity);
	}

	@Override
	public void updateEntity(T entity) {
		this.hibernateTemplate.update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<T> queryEntityList(Pagination<T> page, QueryParameters param) {
		T model = page.getModel();
		DetachedCriteria criteria = DetachedCriteria.forClass(model.getClass());
		//查询条件
		Map<String, String> searchMap = param.getSearchConditions();
		Set<Entry<String, String>> searchSet = searchMap.entrySet();
		for(Entry<String, String> entry : searchSet){
			
			Object obj = null;
			try {
				Field field = model.getClass().getDeclaredField(entry.getKey());
				field.setAccessible(true);
				obj = field.get(model);
			} catch (Exception e) {
				logger.warn("给sql赋值失败["+entry.getKey()+"]已跳过……");
				continue;
			}
			
			if(entry.getValue().equals(Conditions.Search.eq.toString())){
				criteria.add(Restrictions.eq(entry.getKey(), obj));
			}else if(entry.getValue().equals(Conditions.Search.lt.toString())){
				criteria.add(Restrictions.lt(entry.getKey(), obj));
			}else if(entry.getValue().equals(Conditions.Search.gt.toString())){
				criteria.add(Restrictions.gt(entry.getKey(), obj));
			}else if(entry.getValue().equals(Conditions.Search.like.toString())){
				criteria.add(Restrictions.like(entry.getKey(), obj.toString(), MatchMode.ANYWHERE));
			}
		}
		//排序规则
		Map<String, String> orderMap = param.getSearchConditions();
		Set<Entry<String, String>> orderSet = orderMap.entrySet();
		for(Entry<String, String> entry : orderSet){
			if(entry.getValue().equals(Conditions.Order.asc.toString())){
				Order order = Order.asc(entry.getKey());
				criteria.addOrder(order);
			}else if(entry.getValue().equals(Conditions.Order.desc.toString())){
				Order order = Order.desc(entry.getKey());
				criteria.addOrder(order);
			}
		}
		List<T> list = (List<T>) this.hibernateTemplate.findByCriteria(criteria, page.getItemStartIndex(), page.getItemEndIndex());
		page.setList(list);
		criteria.setProjection(Projections.projectionList().add(Projections.rowCount())).add(Example.create(model.getClass()));
		Object obj = hibernateTemplate.findByCriteria(criteria).iterator().next();
		page.setRowCount((Integer)obj);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<T> queryEntityListBySql(final Pagination<T> page, final String queryString, final Object[] params) {
		List<T> list = 	(List<T>) this.hibernateTemplate.executeWithNativeSession(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(queryString);
				int index = 0;
				for(Object param : params){
					query.setParameter(index, param);
					index ++;
				}
				query.setFirstResult(page.getItemStartIndex());
				query.setMaxResults(page.getItemEndIndex());
				return query.list();
			}
		});
		page.setList(list);
		return page;
	}

	@Override
	public T queryOne(T entity) {
		return (T) this.hibernateTemplate.findByExample(entity).get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T queryOne(String entityName, Serializable id) {
		return (T) this.hibernateTemplate.get(entityName, id);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}