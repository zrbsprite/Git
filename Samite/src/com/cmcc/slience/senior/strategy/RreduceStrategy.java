package com.cmcc.slience.senior.strategy;

public class RreduceStrategy implements IStrategy {

	@Override
	public void execute(int x, int y) {
		System.out.println(x - y);
	}

}
