package com.jsprite.core;


public interface IService<T> {

	@SuppressWarnings("hiding")
	public <T> T find(Class<T> clazz, String id);
	
	public Pagination<T> query(Pagination<T> page);
	
	public void add(T model);
	
	public void delete(T model);
}
