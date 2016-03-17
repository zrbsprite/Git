/**
 * File Name:PlayerHandler.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月14日下午3:58:08
 */
package com.cmcc.slience.senior.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月14日下午3:58:08 <br>
 * E-mail:  <br> 
 */
public class PlayerHandler implements InvocationHandler {

	private Object obj;
	
	public PlayerHandler(Object obj){
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我是代理");
		return method.invoke(obj, args);
	}

}
