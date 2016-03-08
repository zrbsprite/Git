package com.cmcc.slience.senior.thread.junior;

public class NumberFactory {

	private int i = 0;
	
	private Object lock = new Object();
	
	public void create(){
		synchronized (lock) {
			System.out.println("create ...i_" + i++);
		}
	}
	
	public void consume(){
		synchronized(lock){
			System.out.println("create ...i-" + i);
		}
	}
	
}
