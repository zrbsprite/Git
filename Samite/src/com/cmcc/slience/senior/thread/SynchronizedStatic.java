package com.cmcc.slience.senior.thread;

/**
 * 描述：静态代码块同步对所有的对象都有效,因为静态的方法不能直接访问非静态的成员变量<br>
 * 作者：ZRB <br>
 * 修改日期：2015年2月11日下午4:48:16 <br> <br/>
 */
public class SynchronizedStatic {

	private static int i;
	
	public synchronized static void up(){
		i++;
		System.out.println("id--" + Thread.currentThread().getId()+ ":" + i);
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			
		}
	}
	
	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run() {
				SynchronizedStatic.up();
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				SynchronizedStatic.up();
				//此时up已经执行完成不会影响up的等待时间
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					
				}
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				SynchronizedStatic.up();
			}
		}.start();
	}
}
