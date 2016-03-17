package com.cmcc.slience.senior.thread.pc1;

public class Producer implements Runnable {

	// 每次生产的产品数量
	private int num = 10;

	// 所在放置的仓库
	private Storage storage;

	// 构造函数，设置仓库
	public Producer(Storage storage, int num) {
		this.num = num;
		this.storage = storage;
	}

	// 调用仓库Storage的生产函数
	public void produce(int num) {
		storage.produce(num);
	}

	@Override
	public void run() {
		produce(num);
	}
	
	// get/set方法
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
