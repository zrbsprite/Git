package com.jsprite.web.init;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * applicationontext和使用MVC之后的webApplicationontext会两次调用上面的方法，如何区分这个两种容器呢？
 * 但是这个时候，会存在一个问题，在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,
 * 另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
 * */
public class ApplicationStartedBean implements ApplicationListener<ApplicationEvent> {

	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		logger.info("ApplicationStartedListener监听器启动监听到Spring容器已成功启动，现在你可以做一些你想做的事情了……");
	}
}
