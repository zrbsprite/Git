package com.jsprite.core;

import java.util.List;

/**
 * @author JSprite
 * 使用Adapter模式设计DAO
 * @param <T>
 */
public interface IDao<T> {

	@SuppressWarnings("hiding")
	public <T> T findById(String id);
	
	public List<T> query(T model);
	
	public void add(T model);
	
	public boolean deleteById(String id);
}
