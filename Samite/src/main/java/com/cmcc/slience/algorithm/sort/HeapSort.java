package com.cmcc.slience.algorithm.sort;

import org.junit.Test;

/**
 * 描述：对堆进行改进，主要是数组索引从0开始,降序排列使用最小堆，生序排序使用最大堆<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月18日上午9:17:38 <br>
 * @param <T>
 */
public class HeapSort<T extends Comparable<T>> {

	private int getLeftChild(int hole){
		return hole * 2 + 1;
	}
	
	private void percolateDown(T[] array, int hole, int realSize){
		T element = array[hole];
		int child = 0;
		for(;getLeftChild(hole)<realSize;hole=child){
			child = getLeftChild(hole);
			//如果有右儿子，找出左右儿子中比较小的
			if(child+1<realSize && array[child+1].compareTo(array[child])<0){
				child++;
			}
			//比较父子节点
			if(array[child].compareTo(element)<0){
				array[hole]=array[child];
			}else{
				break;
			}
		}
		array[hole] = element;
	} 
	
	public void heapSort(T[] array){
		//build heap
		int realSize = array.length;
		for(int index=realSize/2;index>=0;index--){
			percolateDown(array, index, realSize);
		}
		
			//Loop delete minvalue
		for(int index=realSize-1;index>0;index--){
			swapMin(array, 0, index);
			percolateDown(array, 0, index);
		}
	}
	
	/**
	 * 方法名称: swapMin<br>
	 * 描述：			<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月18日下午1:13:04<br/>
	 * @param array
	 * @param i
	 * @param index
	 */
	private void swapMin(T[] array, int i, int index) {
		T temp = array[i];
		array[i] = array[index];
		array[index] = temp;
	}

	@Test
	public void test(){
		Integer[] array = new Integer[]{11,5,7,2,3,17};
		//Integer[] array = new Integer[]{10,11,9,15,8,7,6,16};
		HeapSort<Integer> heapsort = new HeapSort<Integer>();
		heapsort.heapSort(array);
		for(Integer one : array){
			System.out.println(one+",");
		}
	}
}
