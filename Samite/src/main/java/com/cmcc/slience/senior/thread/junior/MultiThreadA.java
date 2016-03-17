package com.cmcc.slience.senior.thread.junior;

public class MultiThreadA extends Thread{

	/*
	 * 只是将变量改成静态的
	 */
	public static int MAX_VALUE=0;
	
	@Override
	public void run() {
		while(true){
			if(MAX_VALUE >= 50){
				break;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MAX_VALUE ++;
			System.out.println(currentThread().getName()+":" + MAX_VALUE);
		}
	}
	
	public static void main(String[] args) {
		MultiThreadA one = new MultiThreadA();
		MultiThreadA two = new MultiThreadA();
		MultiThreadA three = new MultiThreadA();
		one.start();
		two.start();
		three.start();
	}
}
