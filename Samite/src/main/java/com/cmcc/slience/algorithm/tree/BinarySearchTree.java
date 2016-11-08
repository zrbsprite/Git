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
	
	public boolean contains(T object){
		return isContain(object, root);
	}
	
	public void add(T element){
		root = add(element, root);
	}
	
	public T remove(T element){
		
		return (T) remove(element, root);
	}
	
	public T findMin(){
		if(isEmpty()){
			return null;
		}
		return (T) findMin(root).element;
	}
	
	public T findMax(){
		if(isEmpty()){
			return null;
		}
		return (T) findMin(root).element;
	}
	
	private BinaryNode<T> add(T element, BinaryNode<T> tree){
		if(null==tree)
			return new BinaryNode<T>(element);
		int scale = element.compareTo((T) tree.element);
		//相等的情况
		if(scale<0){
			tree = add(element, tree.left);
		}else if(scale>0){
			tree = add(element, tree.right);
		}else{
			tree.times ++;
		}
		return tree;
	}
	
	private BinaryNode<T> remove(T element, BinaryNode<T> tree){
		if(null==tree)
			return tree;
		int scale = element.compareTo((T) tree.element);
		//如果小于当前节点值，那么继续检索左子树
		if(scale<0){
			tree = remove(element, tree.left);
		}else if(scale>0){
			tree = remove(element, tree.right);
		}else{
			//如果要删除的就是当前节点，并且此节点有两个子节点即左右节点
			if(tree.left!=null && tree.right!=null){
				//用当前节点的右子树的最小节点的值替换当前值，并且递归替换右子树
				tree.element = findMin(tree.right).element;
				tree.right = remove((T) tree.element, tree.right);
			}else{
				//如果当前节点只有一个儿子节点，那么用不为null的子节点替换当前节点
				tree = (tree.left==null)?tree.right:tree.left;
			}
		}
		return tree;
	}
	
	/**
	 * 方法名称: findMin<br>
	 * 描述：二叉查找树，左子树的最后一个几点既是最小的值;这里使用尾递归<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月8日上午10:15:11<br/>
	 * @param node
	 * @return
	 */
	private BinaryNode<T> findMin(BinaryNode<T> node){
		 if(null==node){
			 return null;
		 }
		 if(null==node.left){
			 return node;
		 }
		 return findMin(node.left);
	}
	
	/**
	 * 描述：二叉查找树，右子树的最后一个几点既是最大的值，这里使用循环<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月8日上午10:14:14<br/>
	 * @param node
	 * @return
	 */
	private BinaryNode<T> findMax(BinaryNode<T> node){
		if(null==node)
			return null;
		while(null!=node.right){
			node = node.right;
		}
		return node;
	}

	 /* 描述：	<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年11月4日上午11:30:26<br/>
	 * @param comp
	 * @param root2
	 * @return
	 */
	private boolean isContain(T object, BinaryNode<T> root) {
		if(null==root)
			return false;
		int result = object.compareTo((T)root.element);
		if(result<0){
			return isContain(object, root.left);
		}else if(result>0){
			return isContain(object, root.right);
		}
		return true;
	}

	private static class BinaryNode<T> {

		Comparable<T> element;

		BinaryNode<T> left;

		BinaryNode<T> right;
		
		int times = 1;
		
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