package com.penzias.core;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BasicService<E, M> {

	public List<M> list(E example);
	
	public M getById(Object id);
	
	public int getTotal(E example);
	
	public int update(@Param("model") M model, @Param("example") E example);

	public int updateById(@Param("model") M model);
	
	public int deleteById(Object id);

	public int delete(E example);
	
	public int add(@Param("model") M model);
}
