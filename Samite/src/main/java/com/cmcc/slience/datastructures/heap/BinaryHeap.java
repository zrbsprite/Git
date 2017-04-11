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

	private final int defaultSize = 11;

	private int realSize = 0;
	
	private Object[] heap;

	public BinaryHeap() {
		heap = new Object[defaultSize];
	}

	public BinaryHeap(int size) {
		heap = new Object[size];
	}
	
	public BinaryHeap(T[] items) {
		heap = new Object[items.length];
		for(T t : items){
			add(t);
			realSize++;
		}
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
		T root = findMin();
		//首先被挖空的是根节点
		int hole = 1;
		//将根节点删除，并将最后一个叶子节点放于根处，同时堆的大小减一
 		heap[hole] = heap[realSize--];
		percolateDown(hole);
		heap[realSize+1]=null;
		return root;
	}

	/**
	 * 方法名称: findMin<br>
	 * 描述：最小堆的最小元素应该在根部，根据堆序性可知就是数组的第一个元素index:1<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月16日上午8:57:31<br/>
	 * @return
	 */
	public T findMin(){
		return (T)heap[1];
	}
	
	public void buildHeap(T[] items){
		realSize = items.length;
		heap = new Object[realSize+1+realSize*3/2];
		int index=1;
		for(T t : items){
			heap[index++]=t;
		}
		buildHeap();
	}
	
	
	/**
	 * 方法名称: buildHeap<br>
	 * 描述：		<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月16日上午10:46:11<br/>
	 */
	private void buildHeap() {
		for(int i=realSize/2;i>0;i--){
			percolateDown(i);
		}
	}

	/**
	 * 方法名称: percolateDown<br>
	 * 描述：	下滤<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月16日上午10:52:18<br/>
	 * @param i
	 */
	private void percolateDown(int hole) {
		T element = (T) heap[hole];
		//循环找到新的合适的根节点
		int child;
		//hole*2<=realSize -> 循环的条件是左子节点存在
		//hole代表当前的空是哪个
		for(;hole*2<=realSize;hole=child){
			//找到左子节点
			child = hole*2;
			//左子节点不是最后一个节点,并且左子节点小于右子节点
			if(child!=realSize && ((T)heap[child+1]).compareTo((T)heap[child])<0)
				child++;
			if(((T)heap[child]).compareTo(element)<0){
				heap[hole] = heap[child]; 
			}else{
				break;
			}
		}
		heap[hole] = element;
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
	
	public static void main(String[] args) {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		heap.buildHeap(new Integer[]{10,11,9,15,8,7,6,16});
		System.out.println(heap.deleteMin().toString());
	}
}
