/**
 * File Name:Stack.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年11月3日下午4:38:01
 */
package com.cmcc.slience.algorithm.stack;

import java.util.Arrays;

import org.apache.http.annotation.ThreadSafe;

/**
 * 描述：栈实现<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月3日下午4:38:01 <br>
 * E-mail: <br>
 */
@ThreadSafe
public class Stack {

	private Object[] elementData = new Object[10];

	private int lastIndex = -1;
	
	public <T> T push(T element){
		int tempLenth = lastIndex+1;
		ensureCapacity(tempLenth);
		elementData[tempLenth] = element;
		lastIndex = tempLenth;
		return element;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T pop(){
		if(lastIndex>-1){
			T tempEle = (T) elementData[lastIndex];
			elementData[lastIndex] = null;
			return tempEle;
		}else{
			return null;
		}
	}
	
	private void ensureCapacity(int minCapacity) {
		// 如果数组的原有长度小于目前所需的长度
		int oldCapacity = elementData.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
}
