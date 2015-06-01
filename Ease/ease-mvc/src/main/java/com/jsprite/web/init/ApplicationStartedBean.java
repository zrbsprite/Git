package com.jsprite.web.init;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.web.context.ServletContextAware;

/**
 * applicationontext和使用MVC之后的webApplicationontext会两次调用上面的方法，如何区分这个两种容器呢？
 * 但是这个时候，会存在一个问题，在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,
 * 另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
 * */
public class ApplicationStartedBean implements ApplicationListener<ContextStartedEvent>, ApplicationContextAware, ServletContextAware {

	private Logger logger = Logger.getLogger(getClass());
	
	private ApplicationContext applicationContext;
	
	private ServletContext servletContext;
	
	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		String contextName = applicationContext.getDisplayName();
		logger.info("Spring名称是【"+contextName+"】的applicationContext启动完成...");
		String basepath = servletContext.getRealPath("/");
		servletContext.setAttribute("basepath", basepath);
		logger.info("已经成功将basepath放入servletContext……");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
