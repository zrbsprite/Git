package com.jsprite.core;

import javax.annotation.Resource;

public class BaseService<T> implements IService<T> {

	private BaseDao<T> baseDao;
	
	@SuppressWarnings("hiding")
	public <T> T find(Class<T> clazz, String id) {
		return this.baseDao.find(clazz, id);
	}

	public Pagination<T> query(Pagination<T> page) {
		return this.baseDao.query(page);
	}

	public void add(T model) {
		this.baseDao.add(model);
	}

	public void delete(T model) {
		this.baseDao.delete(model);
	}

	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	@Resource
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
}
