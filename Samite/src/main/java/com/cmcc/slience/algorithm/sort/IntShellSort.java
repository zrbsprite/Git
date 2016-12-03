package com.cmcc.slience.algorithm.sort;

/**
 * 描述：希尔排序<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月18日下午3:12:10 <br>
 * E-mail:  <br> 
 */
public class IntShellSort {

	public void sort(int[] array){
		int len = array.length;
		for(int gap=len/2;gap>0;gap/=2){
			for(int i=gap;i<len;i++){
				for(int j = i - gap; j >= 0; j -= gap){
					if (array[j] > array[j + gap]){
						int temp = array[j];
						array[j] = array[j + gap];
						array[j + gap] = temp;
					}
						
				}
			}
		}
	}
	
	public void rightSort(int[] array){
		if(null==array){
			throw new NullPointerException("被排序数组对象为空！");
		}
		int length = array.length;
		int i,j,gap,temp;
		for(gap=length/2;gap>0;gap/=2){
			for(i=0;i<gap;i++){
				for(j=i;j+gap<length;j+=gap){
					//插入排序
					int right = j + gap;
					if(array[j] > array[right]){
						temp = array[j];
						array[j] = array[right];
						array[right] = temp;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = {10,2,9,11,8,1};
		IntShellSort shell = new IntShellSort();
//		shell.sort(array);
		shell.rightSort(array);
		for(int i=0;i<array.length;i++)
			System.out.println(array[i]);
	}
}
