package com.cmcc.slience.senior.thread.junior;


public class MultiThreadRun extends Thread {

	public static int MAX_VALUE = 0;

	@Override
	public synchronized void run() {
		while (true) {
			if (MAX_VALUE >= 5) {
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MAX_VALUE++;
			System.out.println(currentThread().getName() + ":" + MAX_VALUE);
		}
	}

	public static void main(String[] args) {
		MultiThreadRun one = new MultiThreadRun();
		MultiThreadRun two = new MultiThreadRun();
		MultiThreadRun three = new MultiThreadRun();
		one.start();
		two.start();
		three.start();
	}
}
