package com.ldyt.common;

import java.util.List;

/**
 * 描述：泛型service<br/>
 * 作者：data-zrb <br/>
 * 修改日期：2015年8月24日 - 上午10:12:07<br/>
 * @param entity - T<T>
 * @param example - E<E>
 */
public interface BaseService<T, E> {

	public int addOne(T entity);
	
	public List<T> list(E example);
	
	public T findOneById(String id);
	
	public int count(E example);
	
	public int update( T record, E example);
	
	public int updateById(T record);
	
	public int deleteById(String id);

	public int deleteByExample(E example);
}
