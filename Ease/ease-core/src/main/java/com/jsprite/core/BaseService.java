package com.jsprite.core;


public abstract class BaseService<T> implements IService<T> {

	@SuppressWarnings("hiding")
	public <T> T find(Class<T> clazz, String id) {
		return this.getBaseDao().find(clazz, id);
	}

	public Pagination<T> query(Pagination<T> page) {
		return this.getBaseDao().query(page);
	}

	public void add(T model) {
		this.getBaseDao().add(model);
	}

	public void delete(T model) {
		this.getBaseDao().delete(model);
	}

	public abstract IDao<T> getBaseDao();
}
