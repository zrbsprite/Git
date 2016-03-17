package com.cmcc.slience.senior.thread.junior;

/**
 * 描述：多线程初步<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月2日下午4:38:34 <br>
 * E-mail: <br>
 */
public class MultiThread extends Thread{

	private int MAX_VALUE = 0;
	
	/*
	 * 不考虑线程同步问题，每个线程有着各自的max_value，不存在竞争的问题
	 * 下一版引入公共资源
	 */
	
	@Override
	public void run() {
		while(true){
			if(MAX_VALUE >= 50){
				break;
			}
			MAX_VALUE ++;
			System.out.println(currentThread().getName()+":" + MAX_VALUE);
		}
	}
	
	public static void main(String[] args) {
		MultiThread one = new MultiThread();
		MultiThread two = new MultiThread();
		MultiThread three = new MultiThread();
		one.start();
		two.start();
		three.start();
	}
}
