package com.cmcc.slience.senior.thread.pc;

public abstract class Actor implements Runnable{

	protected Factory factory;
	
	protected Actor(Factory factory){
		this.factory = factory;
	}
	
	public abstract void execute();
	
	@Override
	public void run() {
		while(true){
			execute();
		}
	}
}
