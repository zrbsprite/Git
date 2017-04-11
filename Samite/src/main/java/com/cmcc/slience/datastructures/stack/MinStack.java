package com.cmcc.slience.datastructures.stack;

import java.util.Stack;

import org.junit.Test;

/**
 * 描述：最小栈<br>
 * 作者：ruibo <br>
 * 修改日期：2016年10月27日下午10:24:46 <br>
 * 说明：用一个栈存储数据，同时用另外一个栈存储最小值索引，当push进的值比当前最小值的值小，那么将新push的值的索引放入索引栈中。同理出栈也要做相应处理。
 * 实现最小队列，最大栈，最大队列
 */
public class MinStack {

	private Stack<Integer> stack = new Stack<>();
	
	private Stack<Integer> indexStack = new Stack<>();
	
	public void push(Integer value){
		if(stack.size()==0){
			stack.push(value);
			indexStack.push(0);
		}else{
			if(value<getMinValue()){
				stack.push(value);
				indexStack.push(stack.size()-1);
			}
		}
	}
	
	public Integer pop(){
		if(stack.size()==0){
			return null;
		}
		if(getMinValue()==stack.get(indexStack.lastElement())){
			indexStack.pop();
		}
		return stack.pop();
	}
	
	public Integer getMinValue(){
		if(stack.size()==0){
			return null;
		}
		return stack.get(indexStack.lastElement());
	}
	
	@Test
	public void test(){
		MinStack minStack = new MinStack();
		Integer first = minStack.getMinValue();
		System.err.println(first);
		Integer firstPop = minStack.pop();
		System.out.println(firstPop);
		minStack.push(10);
		System.out.println(minStack.getMinValue());
		minStack.push(10);
		minStack.push(13);
		minStack.push(8);
		minStack.push(7);
		minStack.push(5);
		minStack.push(14);
		minStack.push(2);
		System.out.println(minStack.getMinValue());
		System.out.println(minStack.pop());
		System.out.println(minStack.getMinValue());
	}
}
