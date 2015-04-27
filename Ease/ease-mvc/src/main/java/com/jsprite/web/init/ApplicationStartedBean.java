package com.jsprite.web.init;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * applicationontext和使用MVC之后的webApplicationontext会两次调用上面的方法，如何区分这个两种容器呢？
 * 但是这个时候，会存在一个问题，在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,
 * 另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
 * */
public class ApplicationStartedBean implements ApplicationListener<ApplicationEvent>, ApplicationContextAware {

	private Logger logger = Logger.getLogger(getClass());
	
	private ApplicationContext applicationContext;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof EaseEvent){
			String name = applicationContext.getDisplayName();
			logger.info("The current applicationContext is " + name);
			if(name.startsWith("Root")||name.startsWith("root")){
				logger.info("ApplicationStartedListener监听器启动监听到Spring容器已成功启动，现在你可以做一些你想做的事情了……");
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
