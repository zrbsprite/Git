package com.cmcc.slience.senior.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * �������򵥵�long��������������<br>
 * 	   �̰߳�ȫ<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��2��11������3:26:38 <br> <br/>
 */
public class IdGenerator {

	//id��������Ҫ����
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
