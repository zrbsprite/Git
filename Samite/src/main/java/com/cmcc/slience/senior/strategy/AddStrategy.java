package com.cmcc.slience.senior.strategy;

public class AddStrategy implements IStrategy{

	@Override
	public void execute(int x, int y) {
		System.out.println(x + y);
	}

}
