package com.jsprite.web.commons;

import java.io.Serializable;

import com.jsprite.core.Pagination;


public abstract class BasicServiceImpl<T> implements BaseService<T>{

	protected abstract BaseDao<T> getDao();
	
	@Override
	public Serializable addEntity(T entity) {
		return getDao().addEntity(entity);
	}

	@Override
	public void updateEntity(T entity) {
		getDao().updateEntity(entity);
	}

	@Override
	public Pagination<T> queryEntityList(Pagination<T> page, QueryParameters param) {
		return getDao().queryEntityList(page, param);
	}

	@Override
	public T queryOne(T entity) {
		return getDao().queryOne(entity);
	}

	@Override
	public T queryOne(String entityName, Serializable id) {
		return getDao().queryOne(entityName, id);
	}
}