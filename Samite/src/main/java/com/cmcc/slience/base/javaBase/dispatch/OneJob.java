package com.cmcc.slience.base.javaBase.dispatch;

public class OneJob implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

}
