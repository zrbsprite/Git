/**
 * File Name:BinarySearchTree.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年11月4日上午10:49:30
 */
package com.cmcc.slience.algorithm.tree;

/**
 * 描述：二叉查找树<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月4日上午10:49:30 <br>
 * E-mail: <br>
 * 
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {

	private BinaryNode<T> root;

	public BinarySearchTree() {
		root = null;
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return null == root;
	}
	
	public boolean contains(BinaryNode<T> node){
		return isContain(node, root);
	}

	 /* 描述：	<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月4日上午11:30:26<br/>
	 * @param comp
	 * @param root2
	 * @return
	 */
	private boolean isContain(BinaryNode<T> node, BinaryNode<T> root) {
		if(null==root)
			return false;
		int result = node.element.compareTo((T)root.element);
		if(result<0){
			return isContain(node, root.left);
		}else if(result>0){
			return isContain(node, root.right);
		}
		return true;
	}

	private static class BinaryNode<T> {

		Comparable<T> element;

		BinaryNode<T> left;

		BinaryNode<T> right;

		BinaryNode(Comparable<T> element) {
			this(element, null, null);
		}

		BinaryNode(Comparable<T> element, BinaryNode<T> left, BinaryNode<T> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
	}
}