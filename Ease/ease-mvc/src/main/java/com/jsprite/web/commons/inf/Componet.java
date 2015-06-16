/**
 * File Name:Componet.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年6月16日下午6:42:07
 */
package com.jsprite.web.commons.inf;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年6月16日下午6:42:07 <br>
 * E-mail:  <br> 
 */
public abstract class Componet {

	protected ITree node;
	
	public Componet(ITree node){
		this.node = node;
	}
	
	protected Componet parent = null;
	
	protected void setParent(Componet parent){
		this.parent = parent; 
	}
	
	public Componet getParent(){
		return this.parent;
	}
	
	public ITree getNodeInfo(){
		return node;
	}
}
