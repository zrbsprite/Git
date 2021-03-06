package com.jsprite.web.commons;

import java.io.Serializable;

import com.jsprite.core.Pagination;


public interface BaseService<T> {

	public Serializable addEntity(T entity);
	
	public void updateEntity(T entity);
	
	public Pagination<T> queryEntityList(Pagination<T> page, QueryParameters param);
	
	public T queryOne(T entity);
	
	public T queryOne(String entityName, Serializable id);
	
}
