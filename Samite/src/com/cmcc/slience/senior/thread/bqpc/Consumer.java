package com.cmcc.slience.senior.thread.bqpc;

public class Consumer implements Runnable {

	// 每次消费的产品数量
	private int num = 20;

	// 所在放置的仓库
	private Storage storage;

	// 构造函数，设置仓库
	public Consumer(Storage storage, int num) {
		this.storage = storage;
		this.num = num;
	}

	// 线程run函数
	@Override
	public void run() {
		consume(num);
	}

	// 调用仓库Storage的生产函数
	public void consume(int num) {
		storage.consume(num);
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
