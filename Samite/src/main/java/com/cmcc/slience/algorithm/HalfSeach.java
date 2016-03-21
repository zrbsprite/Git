/**
 * File Name:HalfSeach.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月21日上午10:38:38
 */
package com.cmcc.slience.algorithm;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月21日上午10:38:38 <br>
 * E-mail:  <br> 
 */
public class HalfSeach {


	private void sort(int[] array){
		Arrays.sort(array);
	}
	
	/*这般查找的时间复杂度是 O(log N)*/
	private boolean isContain(int tar, int[] array){
		int start = 0;
		int end = array.length;
		
		while(end>=start){
			int val = (end + start)/2;
			int mid = array[val];
			if(tar == mid){
				return true;
			}else if(mid>tar){
				end = val -1;
			}else if(mid < tar){
				start = val +1;
			}
		}
		return false;
	}
	
	@Test
	public void doSearch(){
		int tar = 21;
		int[] array = new int[]{20,15,21,33,16,8,9,29};
		sort(array);
		Assert.assertTrue(isContain(tar, array));
	}
}
