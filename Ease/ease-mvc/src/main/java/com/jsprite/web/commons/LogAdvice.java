package com.jsprite.web.commons;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;


public class LogAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Logger logger = Logger.getLogger(invocation.getThis().getClass());
		String methodName = invocation.getMethod().getName();
		logger.info("方法["+methodName+"]要开始执行了……");
		return invocation.proceed();
	}


}
