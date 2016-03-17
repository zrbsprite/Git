package com.cmcc.slience.senior.thread.pc;

public class Consume extends Actor{

	protected Consume(Factory factory) {
		super(factory);
	}

	@Override
	public void execute() {
		factory.consume();
	}

}
