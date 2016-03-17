package com.cmcc.slience.senior.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述：简单的long类型主键生成器<br>
 * 	   线程安全<br>
 * 作者：ZRB <br>
 * 修改日期：2015年2月11日下午3:26:38 <br> <br/>
 */
public class IdGenerator {

	//id生成器主要的类
	private static AtomicLong atomicLong = null;
	
	public static long getNextLongId(){
		if(atomicLong==null){
			atomicLong = new AtomicLong();
		}
		return atomicLong.incrementAndGet();
	}
	
	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run() {
				System.out.println(IdGenerator.getNextLongId());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(IdGenerator.getNextLongId());
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				System.out.println(IdGenerator.getNextLongId());
			}
		}.start();
	}
}
