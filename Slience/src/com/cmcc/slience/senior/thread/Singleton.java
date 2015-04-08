package com.cmcc.slience.senior.thread;

public class Singleton {

	private static Singleton bean;
	
	private Singleton(){
		
	}
	
	public static Singleton getInstence(){
		if(bean==null){
			bean = new Singleton();
		}
		return bean;
	}
	
	public int add(int a , int b){
		a=a+1;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a+b;
	}
}
