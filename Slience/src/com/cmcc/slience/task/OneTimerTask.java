package com.cmcc.slience.task;

import java.util.TimerTask;

public class OneTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());		
	}

}
