/**
 * File Name:BinaryTree.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年11月3日下午4:33:59
 */
package com.cmcc.slience.algorithm.tree;

/**
 * 描述：二叉树节点<br>
 * 作者：ZRB <br>
 * 修改日期：2016年11月3日下午4:33:59 <br>
 * E-mail: <br>
 */
public class BinaryTreeNode<T> {

	private T element;

	// 左子树
	private BinaryTreeNode<T> left;

	// 右子树
	private BinaryTreeNode<T> right;

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public BinaryTreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}

	public BinaryTreeNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}

}
