package com.cmcc.slience.algorithm.sort;

import org.junit.Test;

/**
 * 描述：选择排序<br>
 * 作者：ZRB <br>
 * 修改日期：2016年12月2日下午5:07:44 <br>
 * E-mail:  <br> 
 */
public class IntChooseSort {

	/**
	 * 方法名称: doSort<br>
	 * 描述：	从未排好的部分的第一个作为最小（最大）的，然后依次和剩余的比较，如果有更小（更大）的，记下下标，以此作为新的最小（最大）值，继续比较，一趟结束后，然后和第一个进行交换。<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年12月2日下午5:24:09<br/>
	 * @param array
	 */
	public void doSort(int[] array){
		int len = array.length;
		for(int i=0;i<len;i++){
			int minIndex = i,temp;
			for(int j=i+1;j<len;j++){
				if(array[j]<array[minIndex])
					minIndex = j;
			}
			if(minIndex!=i){
				temp = array[minIndex];
				array[minIndex] = array[i];
				array[i] = temp;
			}
		}
	}
	
	@Test
	public void test(){
		IntChooseSort bean = new IntChooseSort();
		int[] array = {10,2,9,11,8,1};
		bean.doSort(array);
		for(int i=0;i<array.length;i++)
			System.out.print(array[i]+",");
	}
}
