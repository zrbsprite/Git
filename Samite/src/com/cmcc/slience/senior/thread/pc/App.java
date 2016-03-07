package com.cmcc.slience.senior.thread.pc;

public class App {

	public static void main(String[] args) {
		Factory factory = new Factory();
		for(int i=0; i<1; i++){
			new Thread(new Productor(factory), "product-"+i).start();;
			new Thread(new Consume(factory), "consume-"+i).start();;
		}
	}
}
