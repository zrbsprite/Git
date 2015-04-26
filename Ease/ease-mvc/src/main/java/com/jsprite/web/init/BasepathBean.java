package com.jsprite.web.init;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ServletContextAware;

import com.jsprite.core.init.IApplicationStarted;

public class BasepathBean implements IApplicationStarted, ServletContextAware {

	private ApplicationContext applicationContext;
	
	private ServletContext servletContext;
	
	@Override
	public void startOperat() {
		String basepath = servletContext.getRealPath("/");
		servletContext.setAttribute("basepath", basepath);
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Resource
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
