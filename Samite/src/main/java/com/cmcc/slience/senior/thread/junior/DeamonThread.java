package com.cmcc.slience.senior.thread.junior;

/**
 * 描述：守护线程，就是无论线程是否执行完，只要主线程一退出线程就退出<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月7日下午5:28:29 <br>
 * E-mail: <br>
 *
 */
public class DeamonThread {

	public static void main(String[] args) {
		runDeamon(true);
//		runDeamon(false);
	}

	private static void runDeamon(boolean isDeamon) {
		Thread one = new Thread(){
			public void run() {
				for(int i=0; i<100; i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("one: " + i);
				}
			};
		};
		if(isDeamon){
			//让one成为守护线程
			one.setDaemon(true);
		}
		one.start();
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("main: " + i);
		}
	}
}
