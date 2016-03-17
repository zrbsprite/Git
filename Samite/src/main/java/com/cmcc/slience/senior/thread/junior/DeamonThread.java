package com.cmcc.slience.senior.thread.junior;

/**
 * �������ػ��̣߳����������߳��Ƿ�ִ���ֻ꣬Ҫ���߳�һ�˳��߳̾��˳�<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��3��7������5:28:29 <br>
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
			//��one��Ϊ�ػ��߳�
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
