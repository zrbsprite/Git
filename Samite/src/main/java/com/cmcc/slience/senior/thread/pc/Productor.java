package com.cmcc.slience.senior.thread.pc;

public class Productor extends Actor{

	protected Productor(Factory factory) {
		super(factory);
	}

	@Override
	public void execute() {
		factory.create();
	}
}
