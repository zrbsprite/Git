package com.cmcc.slience.senior.thread.bqpc;

public class App {

	public static void main(String[] args) {
		// �ֿ����
		Storage storage = new Storage();
		// �����߶���
		for(int i=0;i<7;i++){
			new Thread(new Producer(storage, (i+1)*5)).start();
		}
		// �����߶���
		for(int i=0;i<3;i++){
			new Thread(new Consumer(storage, (i+1)*10)).start();
		}
	}
}
