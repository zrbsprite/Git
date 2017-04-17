package com.ldyt.common;

import java.util.List;

/**
 * 描述：BaseService泛型实现类<br/>
 * 作者：data-zrb <br/>
 * 修改日期：2015年10月12日 - 下午5:32:58<br/>
 * @param entity - T<T>
 * @param example - E<E>
 *
 */
public abstract class BaseServiceImpl<T, E> implements BaseService<T, E> {

	public abstract BaseDao<T, E> dao();
	
	@Override
	public int addOne(T entity){
		return this.dao().insert(entity); 
	}

	@Override
	public List<T> list(E example){

		return this.dao().selectByExample(example);
	}

	@Override
	public T findOneById(String id){

		return this.dao().selectByPrimaryKey(id);
	}

	@Override
	public int count(E example){

		return this.dao().countByExample(example);
	}

	@Override
	public int update(T record,E example){

		return this.dao().updateByExampleSelective(record,example);
	}

	@Override
	public int updateById(T record){

		return this.dao().updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteById(String id){

		return this.dao().deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByExample(E example){

		return this.dao().deleteByExample(example);
	}
}
