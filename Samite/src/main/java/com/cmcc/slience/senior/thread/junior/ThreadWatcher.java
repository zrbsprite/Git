package com.cmcc.slience.senior.thread.junior;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 描述：监控线程异常退出并作出处理<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月7日下午5:05:47 <br>
 */
public class ThreadWatcher {

	static class ThreadWatcherRunnable implements Runnable{
		@Override
		public void run() {
			throw new Error("线程异常");
		}
	}
	
	public static void main(String[] args) {
		Thread thread = new Thread(new ThreadWatcherRunnable());
		thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName());
				System.out.println(e.getMessage());
			}
		});
		thread.start();
	}
}
