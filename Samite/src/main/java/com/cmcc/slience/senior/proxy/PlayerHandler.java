/**
 * File Name:PlayerHandler.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015��4��14������3:58:08
 */
package com.cmcc.slience.senior.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**������<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��4��14������3:58:08 <br>
 * E-mail:  <br> 
 */
public class PlayerHandler implements InvocationHandler {

	private Object obj;
	
	public PlayerHandler(Object obj){
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("���Ǵ���");
		return method.invoke(obj, args);
	}

}
