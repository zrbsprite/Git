package com.cmcc.slience.algorithm.sort;

import org.junit.Test;

/**
 * 描述：冒泡排序<br>
 * 作者：ZRB <br>
 * 修改日期：2016年12月2日下午4:39:51 <br>
 */
public class IntBubbleSort {

	/* i=0
	 * 5 4 6 3 1 2
	 * 4 5 6 3 1 2
	 * 4 5 6 3 1 2
	 * 3 5 6 4 1 2
	 * 1 5 6 4 3 2
	 * 
	 * i=1
	 * 1 5 6 4 3 2
	 * 1 4 6 5 3 2
	 * 1 3 6 5 4 2
	 * 1 2 6 5 4 3
	 * ...
	 */
	public void doSort(int[] array){
		int len = array.length;
		for(int i=0;i<len;i++){
			for(int j=i+1;j<len;j++){
				if(array[i]>array[j]){
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}
	
	@Test
	public void test(){
		IntBubbleSort bean = new IntBubbleSort();
		int[] array = {10,2,9,11,8,1};
		bean.doSort(array);
		for(int i=0;i<array.length;i++)
			System.out.print(array[i]+",");
	}
}
