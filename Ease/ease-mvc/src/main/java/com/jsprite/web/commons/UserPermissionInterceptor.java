/**
 * File Name:UserPermissionInterceptor.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月29日下午8:08:42
 */
package com.jsprite.web.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**描述：用户权限拦截器<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月29日下午8:08:42 <br>
 * E-mail:  <br> 
 * 也可以实现 HandlerInterceptor接口 这里采用适配器模式,因为不需要实现全部的方法
 */
public class UserPermissionInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = Logger.getLogger(getClass());
	
	/**方法名称：preHandle <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年4月29日下午8:12:35 
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object) 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURL().toString();
		Subject currentUser = SecurityUtils.getSubject();   
        /**判断是否已经授权*/  
        if(!currentUser.isPermitted(requestUrl)){   
             logger.info("没有有权限");  
             return false;
         }  
		return true;
	}
	
}
