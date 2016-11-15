package com.cmcc.slience.datastructures.heap;

import java.util.Arrays;

/**
 * 描述：二叉堆，优先队列一般使用二叉堆实现<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月15日上午10:00:24 <br>
 * 
 * @param <T>
 */
public class BinaryHeap<T extends Comparable<T>> {

	private final int defaultSize = 10;

	private int realSize = 0;
	
	private Object[] heap;

	public BinaryHeap() {
		heap = new Object[defaultSize];
	}

	public BinaryHeap(int size) {
		heap = new Object[size];
	}

	/**
	 * 方法名称: add<br>
	 * 描述：新增元素<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月15日上午11:16:17<br/>
	 * @param element
	 */
	public void add(T element){
		if(null==element)
			throw new NullPointerException();
		ensureCapacity(realSize+1);
		int index= ++realSize;
		for(; index>0&&element.compareTo(((T)heap[index]))<0; index/=2)
			heap[index] = heap[index/2];
		heap[index] = element;
	}

	/**
	 * 方法名称: deleteMin<br>
	 * 描述：删除最小元素，优先队列最小元素在根节点	<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月15日上午11:18:57<br/>
	 * @return 最小元素
	 */
	public T deleteMin() {
		int rootIndex = realSize/2;
		T element = (T) heap[rootIndex];
		heap[rootIndex] = null;
		return null;
	}

	public void delete(T element){
		
	}
	
	// 扩容
	private void ensureCapacity(int minCapacity) {
		if (minCapacity - heap.length > 0)
            grow(minCapacity);
	}

	private void grow(int minCapacity) {
		// overflow-conscious code
		int oldCapacity = heap.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0)
			newCapacity = minCapacity;
		// minCapacity is usually close to size, so this is a win:
		heap = Arrays.copyOf(heap, newCapacity);
	}
}
