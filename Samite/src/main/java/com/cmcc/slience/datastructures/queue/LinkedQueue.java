package com.cmcc.slience.datastructures.queue;

/**
 * 描述：链表队列<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月14日下午3:38:44 <br>
 * E-mail:  <br> 
 * @param <T>
 */
public class LinkedQueue<T> implements Queue<T> {

	private class Node{
		
		private T element;
		
		private Node next;
		
		Node(T element){
			this.element = element;
		}
		
		@SuppressWarnings("unused")
		Node(T element, Node next){
			this.element = element;
			this.next = next;
		}
	}
	
	private Node start;
	
	private Node end;
	
	private int size = 0;
	
	/**
	 * 方法名称：push <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:38:49 
	 * @see com.cmcc.slience.datastructures.queue.Queue#push(java.lang.Object) 
	 * @param element
	 * @return
	 */
	@Override
	public T push(T element) {
		Node node = new Node(element);
		if(null!=end){
			end.next = node;
			end = node;
		}else{
			end = node;
			start = node;
		}
		size++;
		return node.element;
	}

	/**
	 * 方法名称：pop <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:38:49 
	 * @see com.cmcc.slience.datastructures.queue.Queue#pop() 
	 * @return
	 */
	@Override
	public T pop() {
		if(isEmpty())
			throw new IndexOutOfBoundsException("Empty queue");
		T element = start.element;
		Node node = start.next;
		start = node;
		return element;
	}

	/**方法名称：size <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:38:49 
	 * @see com.cmcc.slience.datastructures.queue.Queue#size() 
	 * @return
	 */
	@Override
	public int size() {
		return size;
	}

	/**方法名称：isEmpty <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2016年11月14日下午3:38:49 
	 * @see com.cmcc.slience.datastructures.queue.Queue#isEmpty() 
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}
