package com.cmcc.slience.senior.thread.pc;

public class Factory {

	int i = 0;
	
	private boolean isCreated = false;
	
	public synchronized void create(){
		while(isCreated){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		i++;
		System.out.println(Thread.currentThread().getName() + "--- " + i);
		isCreated = true;
		notifyAll();
	}
	
	public synchronized void consume(){
		while(isCreated){
			System.out.println(Thread.currentThread().getName() + "--- " + i);
			isCreated = false;
			notifyAll();
		}
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
