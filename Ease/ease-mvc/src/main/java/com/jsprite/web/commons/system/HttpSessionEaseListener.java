/**
 * File Name:EASESessionListener.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月21日下午1:37:59
 */
package com.jsprite.web.commons.system;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月21日下午1:37:59 <br>
 * E-mail:  <br> 
 */
public class HttpSessionEaseListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	private AtomicLong sum = new AtomicLong();
	
	/**方法名称：sessionCreated <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年5月21日下午1:37:59 
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent) 
	 * @param se
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		sum.incrementAndGet();
	}

	/**方法名称：sessionDestroyed <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年5月21日下午1:37:59 
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent) 
	 * @param se
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		sum.decrementAndGet();
	}

	/**方法名称：attributeAdded <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年5月21日下午1:45:35 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent) 
	 * @param se
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		
	}

	/**方法名称：attributeRemoved <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年5月21日下午1:45:35 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent) 
	 * @param se
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		
	}

	/**方法名称：attributeReplaced <br>
	 * 描述： <br>
	 * 作者：ZRB <br>
	 * 修改日期：2015年5月21日下午1:45:35 
	 * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent) 
	 * @param se
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

}
