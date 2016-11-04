/**
 * File Name:TreeNode.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年11月3日上午11:40:24
 */
package com.cmcc.slience.algorithm.tree;

/**
 * 描述：树节点，链表形式<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月3日上午11:40:24 <br>
 * @param <T>
 */
public class TreeNode<T> {

	private T element;

	private TreeNode<T> firstChild;

	private TreeNode<T> nextSubling;

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public TreeNode<T> getFirstChild() {
		return firstChild;
	}

	public void setFirstChild(TreeNode<T> firstChild) {
		this.firstChild = firstChild;
	}

	public TreeNode<T> getNextSubling() {
		return nextSubling;
	}

	public void setNextSubling(TreeNode<T> nextSubling) {
		this.nextSubling = nextSubling;
	}
}
