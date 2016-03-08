package com.cmcc.slience.senior.thread.bqpc;

public class Consumer implements Runnable {

	// ÿ�����ѵĲ�Ʒ����
	private int num = 20;

	// ���ڷ��õĲֿ�
	private Storage storage;

	// ���캯�������òֿ�
	public Consumer(Storage storage, int num) {
		this.storage = storage;
		this.num = num;
	}

	// �߳�run����
	@Override
	public void run() {
		consume(num);
	}

	// ���òֿ�Storage����������
	public void consume(int num) {
		storage.consume(num);
	}

	// get/set����
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}
}
