package com.jsprite.web.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;

import com.jsprite.core.init.IApplicationStarted;

public class ApplicationInitBean implements InitializingBean {
	
	private Logger logger = Logger.getLogger(ApplicationStartedListener.class);
	
	private IApplicationStarted applicationStarted;
	
	private List<IApplicationStarted> applicationStarteds = new ArrayList<IApplicationStarted>();
	
	private ApplicationContext applicationContext;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(null!=applicationStarted){
			applicationStarted.startOperat();
			logger.info("Bean<"+applicationStarted.getClass()+">启动并执行……");
		}
		if(applicationStarteds.size()>0){
			for(IApplicationStarted app : applicationStarteds){
				app.startOperat();
				logger.info("Bean<"+app.getClass()+">启动并执行……");
			}
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Resource
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public IApplicationStarted getApplicationStarted() {
		return applicationStarted;
	}

	public void setApplicationStarted(IApplicationStarted applicationStarted) {
		this.applicationStarted = applicationStarted;
	}

	public List<IApplicationStarted> getApplicationStarteds() {
		return applicationStarteds;
	}

	public void setApplicationStarteds(
			List<IApplicationStarted> applicationStarteds) {
		this.applicationStarteds = applicationStarteds;
	}
	
}
