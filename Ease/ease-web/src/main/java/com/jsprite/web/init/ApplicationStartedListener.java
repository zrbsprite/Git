package com.jsprite.web.init;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartedListener implements ApplicationListener<ApplicationEvent> {

	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		logger.info("ApplicationStartedListener监听器启动监听到Spring容器已成功启动，现在你可以做一些你想做的事情了……");
	}

}
