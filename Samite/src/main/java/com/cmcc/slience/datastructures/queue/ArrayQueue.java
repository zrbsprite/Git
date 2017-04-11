package com.cmcc.slience.datastructures.queue;

import java.util.Arrays;

/**
 * 描述：数组队列<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月14日下午3:04:59 <br>
 */
public class ArrayQueue<T> implements Queue<T> {

	private final int defaultSize = 10;
	
	private Object[] array;

	// 队首的索引
	private int start = 0;

	// 队尾的索引
	private int end = 0;

	public ArrayQueue() {
		array = new Object[defaultSize];
	}

	public ArrayQueue(int length) {
		array = new Object[length];
	}
	
	public ArrayQueue(Object[] array) {
		this.array = array;
	}

	/**
	 * 方法名称：push <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:07:24
	 * 
	 * @see com.cmcc.slience.datastructures.queue.Queue#push(java.lang.Object)
	 * @param element
	 * @return
	 */
	@Override
	public T push(T element) {
		int len = end + 1;
		ensureCapacity(len);
		this.array[end++] = element;
		return element;
	}

	/**
	 * 方法名称：pop <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:07:24
	 * 
	 * @see com.cmcc.slience.datastructures.queue.Queue#pop()
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Index except 0 but -1");
		T element = (T) array[start];
		array[start++] = null;
		return element;
	}

	/**
	 * 方法名称：size <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:07:24
	 * 
	 * @see com.cmcc.slience.datastructures.queue.Queue#size()
	 * @return
	 */
	@Override
	public int size() {
		return end - start + 1;
	}

	/**
	 * 方法名称：isEmpty <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:07:24
	 * 
	 * @see com.cmcc.slience.datastructures.queue.Queue#isEmpty()
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return start == end;
	}

	//扩容
	private void ensureCapacity(int minCapacity) {
		// 如果数组的原有长度小于目前所需的长度
		int oldCapacity = array.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			// minCapacity is usually close to size, so this is a win:
			array = Arrays.copyOf(array, newCapacity);
		}
	}
}
