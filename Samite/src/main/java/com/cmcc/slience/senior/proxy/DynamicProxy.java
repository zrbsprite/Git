package com.cmcc.slience.senior.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy {

	@SuppressWarnings("unchecked")
	public static <T> T getProxy(Class<T> clazz, InvocationHandler handler){
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), handler);
	}
}
