package com.cmcc.slience.senior.thread.pc1;

public class Producer implements Runnable {

	// ÿ�������Ĳ�Ʒ����
	private int num = 10;

	// ���ڷ��õĲֿ�
	private Storage storage;

	// ���캯�������òֿ�
	public Producer(Storage storage, int num) {
		this.num = num;
		this.storage = storage;
	}

	// ���òֿ�Storage����������
	public void produce(int num) {
		storage.produce(num);
	}

	@Override
	public void run() {
		produce(num);
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
