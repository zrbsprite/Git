package com.cmcc.slience.senior.thread.junior;

public class ThisLockTest {

	public static void main(String[] args) {
		
		ThisLock lock = new ThisLock();
		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.funa();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.funb();
			}
		}).start();
	}
}
