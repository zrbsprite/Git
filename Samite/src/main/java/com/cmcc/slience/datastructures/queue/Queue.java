package com.cmcc.slience.datastructures.queue;

/**描述：队列<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月14日下午2:25:29 <br>
 * E-mail:  <br> 
 * @param <T>
 */
public interface Queue<T> {

	public T push(T object);
	
	public T pop();
	
	public int size();
	
	public boolean isEmpty();
	
}
