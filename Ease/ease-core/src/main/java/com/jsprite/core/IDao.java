package com.jsprite.core;


/**
 * @author JSprite
 * 使用Adapter模式设计DAO
 * @param <T>
 */
public interface IDao<T> {

	@SuppressWarnings("hiding")
	public <T> T find(Class<T> clazz, String id);
	
	public Pagination<T> query(Pagination<T> page);
	
	public void add(T model);
	
	public void delete(T model);
}
