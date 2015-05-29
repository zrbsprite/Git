/**
 * File Name:ApplicationContextBeanUtils.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月29日下午3:57:53
 */
package com.jsprite.web.commons.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**描述：获取spring容器的相关对象<br>
 * 备注：可以在spring中初始化交给spring容器，也可以在web。xml中配置listener,要配置在spring的ContextLoaderListener后面;也可以直接使用,推荐交给spring容器管理</br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月29日下午3:57:53 <br>
 * E-mail:  sireezhang@163.com<br> 
 */
public class ApplicationContextBeanUtils implements InitializingBean, ServletContextListener{

	private Logger logger = Logger.getLogger(ApplicationContextBeanUtils.class);
	
	private static WebApplicationContext webApplicationContext;
	
	static{
		if(webApplicationContext == null){
			webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		}
	}
	
	public static WebApplicationContext getWebApplicationContext(){
		return webApplicationContext;
	}
	
	public static ServletContext getServletContext(){
		return webApplicationContext.getServletContext();
	}
	
	public static Object getBeanByName(String name){
		return webApplicationContext.getBean(name);
	}
	
	public static Object getBeanByName(Class<?> clazz){
		return webApplicationContext.getBean(clazz);
	}
	
	/**方法名称：afterPropertiesSet <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年5月29日下午4:04:53 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet() 
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	}

	/**方法名称：contextInitialized <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年5月29日下午4:11:47 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent) 
	 * @param sce
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}

	/**方法名称：contextDestroyed <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年5月29日下午4:11:47 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent) 
	 * @param sce
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("Web容器销毁了Application！");
	}
}
