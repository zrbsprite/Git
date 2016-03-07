package com.penzias.quartz;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class DynamicQuartzBean {

	@Resource
	private SchedulerFactoryBean schedulerFactoryBean;
	
	/**
	 * @author: Bob
	 * 修改时间：2015年12月4日 - 上午9:13:24<br/>
	 * 功能说明：检查任务是否存在<br/>
	 * @param taskName
	 * @param taskGroup
	 * @return
	 * @throws SchedulerException
	 */
	public boolean judgeTaskExist(String taskName, String taskGroup) throws SchedulerException{
		JobKey jobKey = JobKey.jobKey(taskName, taskGroup);  
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		return scheduler.checkExists(jobKey);
	}
	
	/**
	 * @author: Bob
	 * 修改时间：2015年12月4日 - 上午9:30:12<br/>
	 * 功能说明：添加任务，并交给调度<br/>
	 * @param taskName
	 * @param taskGroup
	 * @param mass
	 * @param job
	 * @throws SchedulerException
	 */
	public void createDynamicTask(String taskName, String taskGroup, CronVO mass, BaseJob job) throws SchedulerException{
		//获取任务调度bean
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		//产生task
		//设置CronExpression
		StringBuffer  cronExpression = new StringBuffer();
		cronExpression.append(mass.getSecond()+" ");
		cronExpression.append(mass.getMinute()+" ");
		cronExpression.append(mass.getHour()+" ");
		cronExpression.append(mass.getDay()+" ");
		cronExpression.append(mass.getMonth()+" ");
		cronExpression.append("? ");
		cronExpression.append(mass.getYear());
		TriggerKey triggerKey = new TriggerKey(taskName, taskGroup);
		CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if(null==cronTrigger){
			JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(taskName, taskGroup).build();
			jobDetail.getJobDataMap().put("name",taskName);
			jobDetail.getJobDataMap().put("group", taskGroup);
			jobDetail.getJobDataMap().put("schedulerFactoryBean", schedulerFactoryBean);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression.toString());  
            cronTrigger = TriggerBuilder.newTrigger().withIdentity(taskName, taskGroup).withSchedule(scheduleBuilder).build();  
            try{
            	scheduler.scheduleJob(jobDetail, cronTrigger); 
			} catch (Exception e){
				e.printStackTrace();
				boolean flag = scheduler.checkExists(triggerKey);
				if(flag){
					JobKey key = new JobKey(taskName, taskGroup);
					scheduler.deleteJob(key);
				}
			}
            return;
		}
		
	}
}
