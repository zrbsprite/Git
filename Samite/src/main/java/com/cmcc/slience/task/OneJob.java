package com.cmcc.slience.task;

public class OneJob implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

}
