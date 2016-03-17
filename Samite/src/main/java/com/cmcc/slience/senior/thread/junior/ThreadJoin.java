package com.cmcc.slience.senior.thread.junior;

public class ThreadJoin {

	public static void main(String[] args) {
		Thread threadOne = new Thread(){
			public void run() {
				int i = 0;
				while(i++<300){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("threadOne: " + i);
				}
			};
		};
		threadOne.start();
		//yield是让线程暂时让出cpu使用权，时间不能有程序控制
		//Thread.yield();
		/*Thread threadTwo = new Thread(){
			public void run() {
				int i = 0;
				while(i++<50){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("threadTwo: " + i);
				}
			};
		};
		threadTwo.start();*/
		try {
			//join是把线程假如到主线程，相当于成了同步代码
			threadOne.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread threadTwo = new Thread(){
			public void run() {
				int i = 0;
				while(i++<50){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("threadTwo: " + i);
				}
			};
		};
		threadTwo.start();
		for(int i=0;i<100;i++){
			System.out.println("main: " + i);
		}
	}
}
