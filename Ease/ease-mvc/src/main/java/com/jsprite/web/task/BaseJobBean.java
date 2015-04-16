package com.jsprite.web.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jsprite.core.utils.LogUtils;

public abstract class BaseJobBean extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LogUtils.info("The job starts excute……");
		excute();
		LogUtils.info("The job excute finished……");
	}
	
	public abstract void excute();
}
