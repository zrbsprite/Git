package com.cmcc.slience.datastructures.queue;

import java.security.InvalidParameterException;

/**描述：循环队列<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月14日下午3:39:43 <br>
 * @param <T>
 */
public class CircleQueue<T> implements Queue<T> {

	private final int defaultSize = 10;
	
	private int head = 0;
	
	private int tail = 0;
	
	private Object[] array;
	
	private int capacity = 0;
	
	public CircleQueue(){
		array = new Object[defaultSize];
		capacity = defaultSize;
	}
	
	public CircleQueue(int length){
		array = new Object[length];
		capacity = length;
	}
	
	public CircleQueue(Object[] array){
		this.array = array;
		capacity = array.length;
	}
	
	/**方法名称：push <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:39:50 
	 * @see com.cmcc.slience.datastructures.queue.Queue#push(java.lang.Object) 
	 * @param object
	 * @return
	 */
	@Override
	public T push(T object) {
		if(null==object)
			throw new InvalidParameterException("The parameter is invalid:null");
		//如果队列已经满了
		if(head == tail && array[tail]!=null)
			throw new IndexOutOfBoundsException("The queue is full");
		array[tail++] = object;
		tail = tail == capacity?0:tail;
		return object;
	}

	/**方法名称：pop <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:39:50 
	 * @see com.cmcc.slience.datastructures.queue.Queue#pop() 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		if(isEmpty())
			throw new IndexOutOfBoundsException("The queue is empty");
		T element = (T) array[head];
		array[head++] = null;
		head = head==capacity?0:head;
		return element;
	}

	/**方法名称：size <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:39:50 
	 * @see com.cmcc.slience.datastructures.queue.Queue#size() 
	 * @return
	 */
	@Override
	public int size() {
		if(isEmpty())
			return 0;
		return tail>head?tail-head:capacity-(head-tail);
	}

	/**方法名称：isEmpty <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:39:50 
	 * @see com.cmcc.slience.datastructures.queue.Queue#isEmpty() 
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return head==tail && null==array[tail];
	}
}
