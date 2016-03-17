package com.cmcc.slience.senior.strategy;

public class Context {

	private IStrategy strategy;
	
	public Context(IStrategy strategy){
		this.strategy = strategy;
	}
	
	public void execute(int x, int y){
		this.strategy.execute(x, y);
	}
	
	public static void main(String[] args) {
		IStrategy strategy = new AddStrategy();
		Context context = new Context(strategy);
		context.execute(10, 12);
	}
}
