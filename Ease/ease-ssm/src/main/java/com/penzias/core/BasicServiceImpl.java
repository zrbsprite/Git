package com.penzias.core;

import java.util.List;

public abstract class BasicServiceImpl<E, M> implements BasicService<E, M> {

	public abstract BasicMapper<E, M> getMapper();
	
	@Override
	public List<M> list(E example){

		return getMapper().selectByExample(example);
	}

	@Override
	public M getById(Object id){
		return getMapper().selectByPrimaryKey(id);
	}

	@Override
	public int getTotal(E example){
		return getMapper().countByExample(example);
	}

	@Override
	public int update(M model,E example){
		return getMapper().updateByExampleSelective(model, example);
	}

	@Override
	public int updateById(M model){
		return getMapper().updateByPrimaryKeySelective(model);
	}

	@Override
	public int deleteById(Object id){
		return getMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int delete(E example){
		return getMapper().deleteByExample(example);
	}

	@Override
	public int add(M model){
		return getMapper().insert(model);
	}

}
