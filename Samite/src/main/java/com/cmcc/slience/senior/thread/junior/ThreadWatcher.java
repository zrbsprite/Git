package com.cmcc.slience.senior.thread.junior;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * ����������߳��쳣�˳�����������<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��3��7������5:05:47 <br>
 */
public class ThreadWatcher {

	static class ThreadWatcherRunnable implements Runnable{
		@Override
		public void run() {
			throw new Error("�߳��쳣");
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
