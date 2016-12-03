package com.cmcc.slience.algorithm.sort;

import org.junit.Test;

public class IntInsertSort{

	public void doSort(int[] array){
		int len = array.length;
		for(int i=1;i<len;i++){
			int j=i;
			int temp = array[i];
			for(;j>0;j--){
				if(temp<array[j-1]){
					array[j]=array[j-1];
				}else
					break;
			}
			array[j] = temp;
		}
	}
	
	public void doSortV1(int[] array){
		int len = array.length;
		int j;
		for(int i=1;i<len;i++){
			int temp = array[i];
			for(j=i;j>0 && temp<array[j-1];j--){
				array[j]=array[j-1];
			}
			array[j] = temp;
		}
	}
	
	public void doSortV2(int[] array){
		int len = array.length;
		int j;
		for(int i=1;i<len;i++){
			int temp = array[i];
			j = i;
			while(j>0&&temp<array[j-1]){
				array[j]=array[j-1];
				j--;
			}
			array[j] = temp;
		}
	}
	
	@Test
	public void test(){
		IntInsertSort bean = new IntInsertSort();
		int[] array = new int[]{2,5,3,8,6,12,15,1,4};
		bean.doSort(array);
		for(int no : array)
			System.out.print(no+",");
	}
	
	@Test
	public void testV1(){
		IntInsertSort bean = new IntInsertSort();
		int[] array = new int[]{2,5,3,8,6,12,15,1,4};
		bean.doSortV1(array);
		for(int no : array)
			System.out.print(no+",");
	}
	
	@Test
	public void testV2(){
		IntInsertSort bean = new IntInsertSort();
		int[] array = new int[]{2,5,3,8,6,12,15,1,4};
		bean.doSortV2(array);
		for(int no : array)
			System.out.print(no+",");
	}
}
