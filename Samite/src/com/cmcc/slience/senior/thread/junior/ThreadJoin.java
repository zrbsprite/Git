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
		//yield�����߳���ʱ�ó�cpuʹ��Ȩ��ʱ�䲻���г������
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
			//join�ǰ��̼߳��絽���̣߳��൱�ڳ���ͬ������
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
