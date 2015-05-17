package com.jsprite.web.commons;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 描述：可以使用@componet但必须在自动扫描的范围之内，也可以在配置文件里面交给spring管理<br>
 * 作者：ruibo <br>
 * 修改日期：2015年5月17日下午12:43:58 <br>
 * E-mail:  <br>
 */
@Aspect
public class LogAspecjBean {

	@Pointcut(value="execution(* com.jsprite.web.controller..*.delete*(..))")
	public void getPoint(){}
	
	/**
	 * 方法名称: doSomething<br>
	 * 描述：   @Before(value = "切入点表达式或命名切入点", argNames = "参数列表参数名")  <br>
	 * 作者: ruibo
	 * 修改日期：2015年5月17日上午10:19:34
	 */
	@Before(value = "getPoint()")
	public void before(JoinPoint point){
		String className = point.getThis().getClass().getName();
		Logger logger = Logger.getLogger(className);
		String methodName = point.getSignature().getName();
		logger.error("方法["+methodName+"]将要被执行……");
	}
	
	/**
	 * 方法名称: after<br>
	 * 描述：后置最终通知,  @After (value="切入点表达式或命名切入点",   argNames="参数列表参数名") 当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）</br>
	 * 作者: ruibo
	 * 修改日期：2015年5月17日上午10:31:18
	 */
	@After(value = "execution(* com.jsprite.web.controller..*.add*(..))")
	public void after(JoinPoint point){
		String className = point.getThis().getClass().getName();
		Logger logger = Logger.getLogger(className);
		String methodName = point.getSignature().getName();
		logger.error("方法["+methodName+"]被执行完毕，还行了增加操作");
	}
	
	/**
	 * 方法名称: afterReturn<br>
	 * 描述：后置返回通知,@AfterReturning(  
     * value="切入点表达式或命名切入点",  
     * pointcut="切入点表达式或命名切入点",  
     * argNames="参数列表参数名",  
     * returning="返回值对应参数名")  在某连接点正常完成后执行的通知，不包括抛出异常的情况<br>
	 * 作者: ruibo
	 * 修改日期：2015年5月17日上午10:30:03
	 */
	@AfterReturning(value = "execution(* com.jsprite.web.controller..*.update*(..))")
	public void afterReturn(JoinPoint point){
		String className = point.getThis().getClass().getName();
		Logger logger = Logger.getLogger(className);
		String methodName = point.getSignature().getName();
		logger.error("方法["+methodName+"]被执行完毕，修改了一条记录");
	}
	
	/**
	 * 方法名称: afterThrowingAdvice<br>
	 * 描述：@AfterThrowing (value="切入点表达式或命名切入点",pointcut="切入点表达式或命名切入点",argNames="参数列表参数名",throwing="异常对应参数名")<br>
	 * 作者: ruibo<br>
	 * 修改日期：2015年5月17日上午11:16:00
	 * @param exception
	 */
	@AfterThrowing(value = "execution(* com.jsprite.web..*.*(..))", throwing = "exception")
	public void afterThrowingAdvice(JoinPoint point, Exception exception) {
		String className = point.getThis().getClass().getName();
		Logger logger = Logger.getLogger(className);
		String methodName = point.getSignature().getName();
		logger.error("方法["+methodName+"]执行抛出异常："+exception.getMessage());
	}
	
	@Around(value = "execution(* com.jsprite.web.controller..*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		long end = System.currentTimeMillis();
		long cost = end - start;
		String className = pjp.getThis().getClass().getName();
		Logger logger = Logger.getLogger(className);
		logger.info("方法["+pjp.getSignature().getName()+"]执行耗费了["+cost+"ms]");
		return retVal;
	}

}
