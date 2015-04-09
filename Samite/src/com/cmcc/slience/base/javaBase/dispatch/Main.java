package com.cmcc.slience.base.javaBase.dispatch;

import java.util.Date;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
//		usingTimer();
		usingScheduledExecutor();
	}
	
	/**
	 * 使用Timer来完成调度
	 */
	public static void usingTimer(){
		Timer timer = new Timer();
		
		OneTimerTask task = new OneTimerTask();
		
		Date firstTime = new Date(); 
		long period = 1*60*1000l;
		timer.schedule(task, firstTime, period);
	}
	
	/**
	 * ScheduledExecutor
	 */
	public static void usingScheduledExecutor(){
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		
		OneJob job = new OneJob();
		Thread thread = new Thread(job); 
		long initialDelay = 1l;
		long period = 1l;
		long delay = 1l;
		service.scheduleAtFixedRate(thread, initialDelay, period, TimeUnit.SECONDS);
		service.scheduleWithFixedDelay(thread, initialDelay, delay, TimeUnit.SECONDS);
	}
	
	
}
