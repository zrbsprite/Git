package com.penzias.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 描述：定时执行群发消息，耦合性较强，是专用的类<br/>
 * 作者：data-zrb <br/>
 * 修改日期：2015年10月12日 - 上午11:47:36<br/>
 */
public abstract class BaseJob implements Job {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * @author: Bob
	 * 修改时间：2015年12月4日 - 上午9:28:56<br/>
	 * 功能说明：是否是一次性的任务<br/>
	 * @return
	 */
	public abstract boolean isOnce();
	
	/**
	 * @author: Bob
	 * 修改时间：2015年12月4日 - 上午9:23:13<br/>
	 * 功能说明：任务的具体操作<br/>
	 */
	public abstract void doTask();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		//删除任务
		String name = context.getMergedJobDataMap().getString("name");
		this.schedulerFactoryBean = (SchedulerFactoryBean) context.getMergedJobDataMap().get("schedulerFactoryBean");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		//执行
		String group = context.getMergedJobDataMap().getString("group");
        JobKey jobKey = JobKey.jobKey(name, group);  
        doTask();
        if(isOnce()){
        	try{
        		scheduler.deleteJob(jobKey);
        		logger.info("删除定时任务 [name:"+name+", group:"+group+"]成功！");
        	} catch (SchedulerException e){
        		logger.error("删除定时任务失败： [name:"+name+", group:"+group+"]");
        		e.printStackTrace();
        	}  
        }
	}
	
	public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean){
		this.schedulerFactoryBean = schedulerFactoryBean;
	}
}
