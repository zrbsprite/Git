package com.jsprite.web.task;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.jsprite.core.annotation.TriggerMethod;
import com.jsprite.core.annotation.TriggerType;

public class AnnotationSchedulerFactoryBean extends SchedulerFactoryBean {

	private Logger logger = Logger.getLogger(AnnotationSchedulerFactoryBean.class);

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	protected void registerJobsAndTriggers() throws SchedulerException {
		try {
			// 获取所有bean name
			String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
			for (String beanName : beanNames) {
				Class<?> targetClass = applicationContext.getType(beanName);
				// 循环判断是否标记了TriggerType注解
				if (targetClass.isAnnotationPresent(TriggerType.class)) {
					Object targetObject = applicationContext.getBean(beanName);
					TriggerType triggerType = (TriggerType) targetClass.getAnnotation(TriggerType.class);
					// 获取时间表达式
					String cronExpression = triggerType.cronExpression();
					String targetMethod = "";
					// 确定标记了TriggerMethod注解的方法名
					Method[] methods = targetClass.getDeclaredMethods();
					for (Method method : methods) {
						if (method.isAnnotationPresent(TriggerMethod.class)) {
							targetMethod = method.getName();
						}
					}
					// 注册定时器业务类
					registerJobs(targetObject, targetMethod, beanName, cronExpression);
					super.registerJobsAndTriggers();
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void registerJobs(Object targetObject, String targetMethod, String beanName, String cronExpression) throws Exception {
		// 声明包装业务类
		MethodInvokingJobDetailFactoryBean jobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
		jobDetailFactoryBean.setTargetObject(targetObject);
		jobDetailFactoryBean.setTargetMethod(targetMethod);
		jobDetailFactoryBean.setBeanName(beanName);
		jobDetailFactoryBean.afterPropertiesSet();

		// 获取JobDetail
		JobDetail jobDetail = jobDetailFactoryBean.getObject();

		// 声明定时器
		CronTriggerFactoryBean cronTriggerBean = new CronTriggerFactoryBean();
		cronTriggerBean.setJobDetail(jobDetail);
		cronTriggerBean.setCronExpression(cronExpression);
		cronTriggerBean.setBeanName(beanName + "_CronTriggerFactoryBean");
		cronTriggerBean.afterPropertiesSet();

		setTriggers(cronTriggerBean.getObject());
	}

}
