/**
 * File Name:MinStack.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年11月7日下午4:57:05
 */
package com.cmcc.slience.algorithm.stack;


/**描述：最小栈<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月7日下午4:57:05 <br>
 * E-mail:  <br> 
 */
public class MaxIntStack {

	private Stack valueStack = new Stack();
	
	private Stack indexStack = new Stack();
	
	public int push(int value){
		if(value<0){
			throw new IllegalArgumentException("小于0的整数！");
		}
		if(isEmpty()){
			valueStack.push(value);
			indexStack.push(0);
		}else{
			int min = valueStack.getElement(indexStack.peer());
			if(min>value){
				valueStack.push(value);
			}else{
				valueStack.push(value);
				int index = valueStack.getLastElementIndex();
				indexStack.push(index);
			}
		}
		return value;
	}
	
	public int pop(){
		if(isEmpty()){
			throw new NullPointerException();
		}
		int last = indexStack.getLastElementIndex();
		int index = indexStack.getElement(last);
		int lastIndex = valueStack.getLastElementIndex();
		if(index==lastIndex){
			indexStack.pop();
			return valueStack.pop();
		}
		return valueStack.pop();
	}
	
	public int getMaxValue(){
		if(isEmpty()){
			throw new NullPointerException();
		}
		return valueStack.getElement(indexStack.peer());
	}
	
	public boolean isEmpty(){
		return valueStack.isEmpty();
	}
}
