package com.jsprite.core;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.HibernateTemplate;

public abstract class BaseDao<T> implements IDao<T>{

	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("hiding")
	public <T> T find(Class<T> clazz, String id) {
		return (T) this.hibernateTemplate.get(clazz , id);
	}

	@SuppressWarnings("unchecked")
	public Pagination<T> query(Pagination<T> page) {
		Class<? extends Object> clazz = page.getModel().getClass();
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		//criteria.add(Restrictions.idEq(page.getModel()));
		fillCriteria(criteria, page.getModel());
		criteria.setProjection(Projections.projectionList().
				add(Projections.rowCount()))  
				.add(Example.create(clazz));
		Object obj = hibernateTemplate.findByCriteria(criteria).iterator().next();
		page.setRowCount((Integer)obj);
		
		criteria.setProjection(null);
		int startIndex = page.getItemStartIndex();
		int endIndex = page.getItemEndIndex();
		page.setList((List<T>) hibernateTemplate.findByCriteria(criteria, startIndex, endIndex));
		
		return page;
	}

	public void add(T model) {
		this.hibernateTemplate.save(model);
	}

	public void delete(T model) {
		this.hibernateTemplate.delete(model);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/**
	 * 组装查询条件
	 * @param criteria
	 * @param t
	 */
	public abstract void fillCriteria(DetachedCriteria criteria, T t);
}
