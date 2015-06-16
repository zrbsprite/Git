/**
 * File Name:ParentNode.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年6月16日下午7:04:54
 */
package com.jsprite.web.commons.inf;

import java.util.ArrayList;
import java.util.List;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年6月16日下午7:04:54 <br>
 * E-mail:  <br> 
 */
public class ParentNode extends Componet {
	
	private List<Componet> subordinateList = new ArrayList<Componet>();

	public ParentNode(ITree node){
		super(node);
	}
	
	public void addChildren(Componet node){
		//设置父节点
		node.setParent(this);
		//添加子节点
		this.subordinateList.add(node);
	}
	
	public List<Componet> getChildren(){
		return this.subordinateList;
	}
}
